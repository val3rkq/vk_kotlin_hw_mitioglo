package com.example.pizza_app.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pizza_app.data.models.Category

class CategoryViewModel : ViewModel() {
    val categories = listOf(
        Category("1", "Pizza"),
        Category("2", "Drinks"),
        Category("3", "Snacks")
    )
}