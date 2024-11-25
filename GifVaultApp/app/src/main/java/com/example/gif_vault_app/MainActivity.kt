package com.example.gif_vault_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.gif_vault_app.data.api.GiphyApiClient
import com.example.gif_vault_app.data.repository.GifRepositoryImpl
import com.example.gif_vault_app.ui.components.ErrorView
import com.example.gif_vault_app.ui.components.GifGrid
import com.example.gif_vault_app.ui.components.LoadingView
import com.example.gif_vault_app.ui.viewmodel.GifViewModel
import com.example.gif_vault_app.ui.viewmodel.GifViewModelFactory
import com.example.gif_vault_app.ui.viewmodel.UiState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // repository and viewModel init
            val repository = GifRepositoryImpl(GiphyApiClient.api)
            val viewModel: GifViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
                factory = GifViewModelFactory(repository)
            )

            // screen
            Scaffold { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                    GifListScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun GifListScreen(viewModel: GifViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    when (uiState) {
        is UiState.Loading -> LoadingView()
        is UiState.Success -> GifGrid((uiState as UiState.Success).gifs, { viewModel.loadGifs() }, viewModel.isLoading)
        is UiState.Error -> ErrorView { viewModel.retry() }
    }
}
