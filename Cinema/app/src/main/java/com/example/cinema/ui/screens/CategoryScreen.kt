package com.example.cinema.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.example.cinema.ui.viewmodel.CategoryViewModel
import com.example.cinema.data.models.categories
import com.example.cinema.ui.components.ErrorView
import com.example.cinema.ui.components.LoadingView
import com.example.cinema.ui.components.MoviesGrid
import com.example.cinema.ui.theme.IconBack
import com.example.cinema.ui.theme.IconNotLiked
import com.example.cinema.ui.viewmodel.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(navController: NavController, categoryId: String, viewModel: CategoryViewModel) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = IconBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("favourite") }) {
                        Icon(
                            imageVector = IconNotLiked,
                            contentDescription = "Localized description"
                        )
                    }
                },
                title = {
                    categories[categoryId]?.name?.let {
                        Text(
                            it,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal
                    )
                )
        ) {
            val uiState by viewModel.uiState.collectAsState()
            when (uiState) {
                is UiState.Loading -> LoadingView()
                is UiState.Success -> MoviesGrid(navController, (uiState as UiState.Success).movies)
                is UiState.Error -> ErrorView { viewModel.load() }
            }
        }
    }
}