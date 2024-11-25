package com.example.gif_vault_app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.PlatformContext
import com.example.gif_vault_app.data.models.GifItem

@Composable
fun GifGrid(gifs: List<GifItem>, loadMoreData: () -> Unit, isLoading: Boolean) {
    // Состояние списка
    val listState = rememberLazyListState()

    // Проверяем достижение конца списка
    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .collect { layoutInfo ->

                val totalItems = layoutInfo.totalItemsCount
                val lastVisibleIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                println(totalItems)
                println(lastVisibleIndex)

                if (!isLoading && totalItems > 0 && lastVisibleIndex >= totalItems - 3) {
                    println("111")
                    loadMoreData()
                }
            }
    }

    // Проверяем ориентацию и устанавливаем количество колонок в зависимости от ориентации экрана
    val columns = if (LocalConfiguration.current.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
        5 // horizontal
    } else {
        2 // vertical
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        val chunkedGifs = gifs.chunked(columns) // Разбиваем на строки, где каждая строка содержит N колонок
        items(chunkedGifs) { rowGifs ->
            Row(modifier = Modifier.fillMaxWidth()) {
                rowGifs.forEach { gif ->
                    Box(
                        modifier = Modifier
                            .weight(1f) // Равномерное распределение колонок
                            .padding(4.dp)
                    ) {
                        GifItemView(gif)
                    }
                }
                // Если в строке не хватает элементов, добавляем пустое место
                repeat(columns - rowGifs.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

        // Индикатор загрузки
        if (isLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}
