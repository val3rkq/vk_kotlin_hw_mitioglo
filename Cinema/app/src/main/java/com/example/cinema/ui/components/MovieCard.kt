package com.example.cinema.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.cinema.data.models.Genres
import com.example.cinema.data.models.MovieModel
import com.example.cinema.data.models.MovieResponse
import com.example.cinema.ui.theme.IconNotLiked
import com.example.cinema.ui.theme.mainColor

@Composable
fun MovieCard(movie: MovieModel, onMovieClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(250.dp)
            .height(300.dp)
            .clickable { onMovieClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Box {
            BackgroundImage(movie, isBlurred = true)

            BackgroundImage(movie)

            TextContent(movie)
        }
    }
}

@Composable
fun BackgroundImage(movie: MovieModel, isBlurred: Boolean = false) {

    val modifier = if (isBlurred) {
        Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .blur(10.dp)
            .alpha(0.8F)
    } else {
        Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
    }

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://image.tmdb.org/t/p/w500/" + movie.posterPath)
            .crossfade(true)
            .build(),
        contentScale = if (isBlurred) ContentScale.FillBounds else ContentScale.Fit,
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun TextContent(movie: MovieModel) {
    // get object of class Genres for converting genres from API num to word
    val genres = Genres()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = IconNotLiked,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )

            RatingCard(movie)

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(color = mainColor)
                .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                .padding(10.dp)
        ) {
            // Movie title
            Text(
                text = movie.title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 18.sp
                ),
                overflow = TextOverflow.Ellipsis
            )

            // Genre
            Text(
                text = genres.getGenresByIds(movie.genreIds),
                style = TextStyle(
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                    fontSize = 14.sp,
                ),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun RatingCard(movie: MovieModel) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(mainColor, RoundedCornerShape(12.dp))
    ) {
        Text(
            text = String.format("%.1f", movie.voteAverage),
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}