package com.example.cinema.ui.components

import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.cinema.data.models.MovieModel

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
                    navController.navigate("movie?movieName=${movie.title}&movieId=${movie.id}")
                },
            )
        }
    }
}