package com.example.cinema.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cinema.data.repository.MoviesRepository
import com.example.cinema.ui.screens.HomeScreen
import com.example.cinema.ui.screens.CategoryScreen
import com.example.cinema.ui.screens.FavouriteScreen
import com.example.cinema.ui.screens.MovieScreen
import com.example.cinema.ui.viewmodel.CategoryViewModel
import com.example.cinema.ui.viewmodel.HomeViewModel
import com.example.cinema.ui.viewmodel.MovieViewModel

@Composable
fun AppNavigation(navController: NavHostController, repository: MoviesRepository) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            val homeViewModel = HomeViewModel()
            HomeScreen(navController, homeViewModel, repository)
        }
        composable("favourite") {
            FavouriteScreen(navController)
        }
        composable("home/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""

            val categoryViewModel = remember { CategoryViewModel(categoryId, repository) }
            CategoryScreen(navController, categoryId, categoryViewModel)
        }
        composable("movie?movieName={movieName}&movieId={movieId}") { backStackEntry ->
            val movieName = backStackEntry.arguments?.getString("movieName") ?: ""
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull() ?: -1

            val movieViewModel = remember { MovieViewModel(movieId, repository) }
            MovieScreen(navController, movieName, movieViewModel)
        }
    }
}