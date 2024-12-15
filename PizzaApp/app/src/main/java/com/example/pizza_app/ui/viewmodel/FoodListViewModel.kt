package com.example.pizza_app.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pizza_app.data.models.Food

class FoodListViewModel : ViewModel() {
    private val foods = listOf(
        Food("1", "1", "Margherita", "Classic pizza with tomatoes and cheese", 10.0),
        Food("2", "1", "Pepperoni", "Spicy pepperoni and cheese", 12.0),
        Food("3", "2", "Coca Cola", "Chilled soft drink", 2.0)
    )

    fun getFoodsByCategory(categoryId: String) = foods.filter { it.categoryId == categoryId }
}