package com.example.cinema.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*

@Composable
fun UnderDevelopmentAnimation() {
    // Load the Lottie animation
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("under_development.json"))
    val progress by animateLottieCompositionAsState(composition, iterations = 1000)

    // Display the Lottie animation centered on the screen
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier.size(300.dp) // Set the desired size
        )
    }
}