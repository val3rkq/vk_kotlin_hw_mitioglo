package com.example.pizza_app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pizza_app.ui.viewmodel.FoodListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FoodListScreen(navController: NavController, categoryId: String, viewModel: FoodListViewModel = viewModel()) {
    val foods = viewModel.getFoodsByCategory(categoryId)

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(foods.size) { index ->
            val food = foods[index]
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        navController.navigate("food_detail/${food.id}")
                    }
            ) {
                BasicText(text = food.name, modifier = Modifier.weight(1f))
            }
        }
    }
}