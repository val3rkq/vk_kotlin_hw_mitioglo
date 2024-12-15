package com.example.pizza_app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pizza_app.ui.viewmodel.FoodDetailViewModel

@Composable
fun FoodDetailScreen(navController: NavController, foodId: String, viewModel: FoodDetailViewModel = viewModel()) {
    val food = viewModel.getFoodDetail(foodId)

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        BasicText(text = food.name, modifier = Modifier.padding(bottom = 8.dp))
        BasicText(text = food.description, modifier = Modifier.padding(bottom = 8.dp))
        BasicText(text = "Price: ${food.price}")
    }
}