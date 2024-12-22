package com.example.cinema.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.data.models.MovieModel
import com.example.cinema.data.repository.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UiState {
    data object Loading : UiState()
    data class Success(val movies: List<MovieModel>) : UiState()
    data class Error(val message: String) : UiState()
}

class CategoryViewModel(private val categoryId: String, private val repository: MoviesRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> get() = _uiState

    private val _movies = MutableStateFlow<List<MovieModel>?>(null)
    private val _isLoading = MutableStateFlow(false)

    init {
        loadMovies()
    }

    // Helper function to get movies based on categoryId
    private suspend fun fetchMovies(categoryId: String): List<MovieModel> {
        return when (categoryId) {
            "1" -> repository.getPopularMovies().getOrThrow().results
            "2" -> repository.getNowPlayingMovies().getOrThrow().results
            "3" -> repository.getTopRatedMovies().getOrThrow().results
            "4" -> repository.getUpcomingMovies().getOrThrow().results
            else -> throw Exception("Category Id is not found")
        }
    }

    private fun loadMovies() {
        if (_isLoading.value) return

        if (_movies.value != null) return

        _isLoading.value = true
        viewModelScope.launch {
            try {
                val newMovies = fetchMovies(categoryId)

                _movies.value = newMovies
                _uiState.value = UiState.Success(_movies.value!!)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "An error occured.")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun load() {
        loadMovies()
    }
}