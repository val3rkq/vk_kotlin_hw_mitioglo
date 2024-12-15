package com.example.pizza_app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pizza_app.ui.viewmodel.CategoryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CategoryScreen(navController: NavController, viewModel: CategoryViewModel = viewModel()) {
    val categories = viewModel.categories

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(categories.size) { index ->
            val category = categories[index]
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        navController.navigate("food_list/${category.id}")
                    }
            ) {
                BasicText(text = category.name, modifier = Modifier.weight(1f))
            }
        }
    }
}