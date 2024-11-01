package com.example.grid_squares_app.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.grid_squares_app.ui.theme.*

@Composable
fun Feed(context: Context, numbers: SnapshotStateList<Int>, modifier: Modifier = Modifier, columns: Int, gridState: LazyGridState) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = modifier
            .fillMaxSize(),
        state = gridState
    ) {
        items(numbers) { number ->
            val currentColor = if (number % 2 == 0) RedColor else BlueColor
            Square(context, number, currentColor)
        }
    }
}