package com.example.cinema.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.data.models.MovieResponse
import com.example.cinema.data.repository.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class MovieUiState {
    data object Loading : MovieUiState()
    data class Success(val movie: MovieResponse) : MovieUiState()
    data class Error(val message: String) : MovieUiState()
}

class MovieViewModel(private val movieId: Int, private val repository: MoviesRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<MovieUiState>(MovieUiState.Loading)
    val movieUiState: StateFlow<MovieUiState> get() = _uiState

    private val _movie = MutableStateFlow<MovieResponse?>(null)
    private val _isLoading = MutableStateFlow(false)

    init {
        loadMovie()
    }

    private fun loadMovie() {
        if (_isLoading.value) return

        if (_movie.value != null) return

        _isLoading.value = true
        viewModelScope.launch {
            try {
                val newMovie = repository.getMovieInfo(movieId).getOrThrow()

                _movie.value = newMovie
                _uiState.value = MovieUiState.Success(_movie.value!!)
            } catch (e: Exception) {
                _uiState.value = MovieUiState.Error(e.localizedMessage ?: "An error occured.")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadMovieInfo() {
        loadMovie()
    }
}