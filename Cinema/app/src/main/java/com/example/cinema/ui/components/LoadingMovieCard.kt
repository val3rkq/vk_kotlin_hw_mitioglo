package com.example.cinema.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun LoadingMovieCard() {
    Box(
        modifier = Modifier
            .width(250.dp)  // Card width
            .height(300.dp)  // Card height
            .clip(RoundedCornerShape(10.dp))
            .shimmer() // the shimmer effect
            .background(Color.Gray)
    )
}