package com.example.gif_vault_app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gif_vault_app.data.models.GifItem

@Composable
fun FullScreenGifView(gif: GifItem?, onClick: () -> Unit) {
    gif?.let {
        GifItemView(it) {}
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Вернуться на главную")
        }
    }
}