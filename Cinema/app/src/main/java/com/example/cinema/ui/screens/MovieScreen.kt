package com.example.cinema.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.cinema.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.cinema.data.models.Genres
import com.example.cinema.data.models.MovieResponse
import com.example.cinema.ui.components.ErrorView
import com.example.cinema.ui.components.LoadingView
import com.example.cinema.ui.theme.IconBack
import com.example.cinema.ui.theme.IconNotLiked
import com.example.cinema.ui.theme.mainColor
import com.example.cinema.ui.theme.textColor
import com.example.cinema.ui.viewmodel.MovieUiState
import com.example.cinema.ui.viewmodel.MovieViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(navController: NavController, movieName: String, viewModel: MovieViewModel) {

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
                title = {
                    Text(
                        movieName,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
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
            ),
        ) {
            val uiState by viewModel.movieUiState.collectAsState()
            when (uiState) {
                is MovieUiState.Loading -> LoadingView()
                is MovieUiState.Success -> MovieContent((uiState as MovieUiState.Success).movie)
                is MovieUiState.Error -> ErrorView { viewModel.loadMovieInfo() }
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun MovieContent(movie: MovieResponse) {

    val genres = Genres()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
            ) {
                // Background image
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/w500/" + movie.backdrop_path)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // title and genres
                Column(modifier = Modifier.weight(3f)) {

                    // Movie title
                    Text(
                        text = movie.title,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp
                        ),
                        overflow = TextOverflow.Ellipsis
                    )

                    // Genres
                    Text(
                        text = genres.getGenres(movie.genres),
                        style = TextStyle(
                            fontWeight = FontWeight.Light,
                            fontSize = 16.sp,
                        ),
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // rating, date, country, language
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    // Rating
                    Box(
                        modifier = Modifier
                            .background(Color(0xFF8A7FFF), RoundedCornerShape(12.dp))
                    ) {
                        Text(
                            text = String.format("%.1f", movie.vote_average),
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    // Date
                    Text(
                        text = movie.release_date,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = textColor
                        )
                    )

                    // Countries
                    Text(
                        text = movie.origin_country.joinToString(", "),
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = textColor
                        )
                    )
                }
            }

            Text(
                text = stringResource(id = R.string.summary),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )

            Text(
                text = movie.overview,
                style = TextStyle(
                    fontSize = 16.sp
                ),
                overflow = TextOverflow.Ellipsis
            )

        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp)),
            colors = ButtonColors(
                contentColor = Color.White,
                disabledContentColor = Color.White,
                containerColor = mainColor,
                disabledContainerColor = mainColor
            ),
            onClick = ( {/*TODO: add to favourite */})
        ) {
            Row (
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = IconNotLiked,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.White
                )

                Text(
                    text = stringResource(id = R.string.add_to_fav),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}