package com.example.cinema.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cinema.data.models.MovieModel
import com.example.cinema.navigation.getMoviePath

@Composable
fun MoviesRow(
    navController: NavController,
    movies: List<MovieModel>,
    rowLength: Int,
) {
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(rowLength) { index ->
            val movie = movies[index]
            MovieCard(
                movie = movie,
                onMovieClick = {
                    navController.navigate(getMoviePath(movie.title, movie.id.toString()))
                },
            )
        }
    }
}