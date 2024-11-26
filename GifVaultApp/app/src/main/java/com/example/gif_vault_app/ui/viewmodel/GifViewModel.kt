package com.example.gif_vault_app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gif_vault_app.data.models.GifItem
import com.example.gif_vault_app.data.repository.GifRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UiState {
    object Loading : UiState()
    data class Success(val gifs: List<GifItem>) : UiState()
    data class FullGif(val gif: GifItem?) : UiState()
    data class Error(val message: String) : UiState()
}

class GifViewModel(private val repository: GifRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> get() = _uiState

    private val _gifs = MutableStateFlow<List<GifItem>>(emptyList())
    val gifs: StateFlow<List<GifItem>> get() = _gifs

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Boolean get() = _isLoading.value

    private var offset = 0
    private val limit = 20

    init {
        loadGifs()
    }

    fun loadGifs() {
        if (_isLoading.value) return // Предотвращение одновременной загрузки

        _isLoading.value = true
        viewModelScope.launch {
            try {
                val newGifs = repository.getTrendingGifs(limit, offset).getOrThrow()
                _gifs.value += newGifs // add new gifs
                offset += limit
                _uiState.value = UiState.Success(_gifs.value) // update state
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "An error occurred.")
            } finally {
                _isLoading.value = false
            }
        }
    }

    // show full gif
    fun updateCurrentGif(newGif: GifItem) {
        viewModelScope.launch {
            _uiState.value = UiState.FullGif(newGif)
        }
    }

    fun hideFullGif() {
        viewModelScope.launch {
            _uiState.value = UiState.Success(gifs.value)
        }
    }

    fun retry() {
        loadGifs()
    }
}