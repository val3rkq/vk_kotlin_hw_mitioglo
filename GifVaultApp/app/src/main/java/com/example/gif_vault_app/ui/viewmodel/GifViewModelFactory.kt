package com.example.gif_vault_app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gif_vault_app.data.repository.GifRepository

class GifViewModelFactory(private val repository: GifRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GifViewModel(repository) as T
    }
}