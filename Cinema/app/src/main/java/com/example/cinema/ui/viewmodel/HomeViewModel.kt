package com.example.cinema.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cinema.data.models.Category

class HomeViewModel : ViewModel() {
    val categories = listOf(
        Category("1", "Popular"),
        Category("2", "Now Playing"),
        Category("3", "Top Rated"),
        Category("4", "Upcoming")
    )
}