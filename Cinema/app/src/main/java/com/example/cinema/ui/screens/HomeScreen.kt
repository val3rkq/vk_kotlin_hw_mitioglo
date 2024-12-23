package com.example.cinema.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cinema.R
import com.example.cinema.ui.viewmodel.HomeViewModel
import com.example.cinema.data.models.Category
import com.example.cinema.data.models.MovieModel
import com.example.cinema.data.repository.MoviesRepository
import com.example.cinema.ui.components.ErrorView
import com.example.cinema.ui.components.LoadingMovieCard
import com.example.cinema.ui.components.MovieCard
import com.example.cinema.ui.components.MoviesRow
import com.example.cinema.ui.components.MoviesRowByCategory
import com.example.cinema.ui.theme.IconNotLiked
import com.example.cinema.ui.viewmodel.CategoryViewModel
import com.example.cinema.ui.viewmodel.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel, repository: MoviesRepository) {
    val categories = viewModel.categories

    val snackbarHostState = remember { SnackbarHostState() }
    val isOffline = false

    LaunchedEffect(isOffline) {
        if (isOffline) {
            snackbarHostState.showSnackbar(
                message = "Not connected",
                duration = SnackbarDuration.Indefinite,
            )
        }
    }

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(stringResource(id = R.string.app_name))
                },
                actions = {
                    IconButton(onClick = { navController.navigate("favourite") }) {
                        Icon(
                            imageVector = IconNotLiked,
                            contentDescription = "Favourite"
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal
                    )
                )
        ) {
            items(categories.size) { index ->
                val category = categories[index]

                Column(
                    modifier = Modifier
                        .height(350.dp)
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = category.name,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )

                        TextButton(onClick = { navController.navigate("home/${category.id}") }) {
                            Text(
                                text = stringResource(id = R.string.show_more),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.Blue
                                ),
                            )
                        }
                    }

                    MoviesRowByCategory(navController, category, repository)
                }
            }
        }
    }
}