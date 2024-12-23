package com.example.cinema.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cinema.data.models.Category
import com.example.cinema.data.models.MovieModel
import com.example.cinema.data.repository.MoviesRepository
import com.example.cinema.ui.viewmodel.CategoryViewModel
import com.example.cinema.ui.viewmodel.UiState

@Composable
fun MoviesRowByCategory(
    navController: NavController,
    category: Category,
    repository: MoviesRepository
) {

    val categoryViewModel = remember { CategoryViewModel(category.id, repository) }
    val uiState by categoryViewModel.uiState.collectAsState()

    when (uiState) {
        is UiState.Loading -> LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(4) {
                LoadingMovieCard()
            }
        }
        is UiState.Success -> MoviesRow(
            navController = navController,
            movies = (uiState as UiState.Success).movies,
            rowLength = 5
        )
        is UiState.Error -> ErrorView { categoryViewModel.load() }
    }
}