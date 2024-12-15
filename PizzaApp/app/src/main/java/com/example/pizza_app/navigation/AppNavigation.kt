package com.example.pizza_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pizza_app.ui.screens.CategoryScreen
import com.example.pizza_app.ui.screens.FoodListScreen
import com.example.pizza_app.ui.screens.FoodDetailScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "categories") {
        composable("categories") {
            CategoryScreen(navController)
        }
        composable("food_list/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
            FoodListScreen(navController, categoryId)
        }
        composable("food_detail/{foodId}") { backStackEntry ->
            val foodId = backStackEntry.arguments?.getString("foodId") ?: ""
            FoodDetailScreen(navController, foodId)
        }
    }
}