package com.example.cinema.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cinema.data.models.MovieModel
import com.example.cinema.ui.viewmodel.CategoryViewModel

@Composable
fun MoviesGrid(
    navController: NavController,
    movies: List<MovieModel>,
) {

    // Determine the number of columns based on the orientation of the screen
    val columns = if (LocalConfiguration.current.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
        5 // Landscape mode
    } else {
        2 // Portrait mode
    }

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 4.dp, horizontal = 10.dp),
        columns = GridCells.Fixed(columns)
    ) {
        items(movies.size) { index ->
            val movie = movies[index]
            Box(
                modifier = Modifier.padding(4.dp)
            ) {
                MovieCard(
                    movie = movie,
                    onMovieClick = {
                        navController.navigate("movie?movieName=${movie.title}&movieId=${movie.id}")
                    },
                )
            }
        }
    }
}