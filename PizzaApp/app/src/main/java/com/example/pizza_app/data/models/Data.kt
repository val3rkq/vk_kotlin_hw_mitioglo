package com.example.pizza_app.data.models

data class Category(val id: String, val name: String)
data class Food(val id: String, val categoryId: String, val name: String, val description: String, val price: Double)
