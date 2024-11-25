package com.example.gif_vault_app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.PlatformContext
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import com.example.gif_vault_app.data.models.GifItem

@Composable
fun GifItemView(gif: GifItem) {

    val isGifLoading = remember { mutableStateOf(true) }

    AsyncImage(
        model = gif.images.original.url,
        contentDescription = null,
        onState = { state ->
            isGifLoading.value = when (state) {
                is AsyncImagePainter.State.Loading -> true
                is AsyncImagePainter.State.Success -> false
                is AsyncImagePainter.State.Error -> false
                else -> false
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .aspectRatio(
                gif.images.original.width.toFloat() /
                        gif.images.original.height.toFloat()
            )
    )

    if (isGifLoading.value) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}