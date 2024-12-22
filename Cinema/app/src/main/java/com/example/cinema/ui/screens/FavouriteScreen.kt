package com.example.cinema.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cinema.ui.components.UnderDevelopmentAnimation
import com.example.cinema.ui.theme.IconBack
import com.example.cinema.ui.viewmodel.FavouriteViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(navController: NavController, viewModel: FavouriteViewModel = viewModel()) {

//    val movies = viewModel.favourites

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
                        "Favourite",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                },
            )
        }
    ) {
        // Lottie animation that this page is under development
        UnderDevelopmentAnimation()
    }
}