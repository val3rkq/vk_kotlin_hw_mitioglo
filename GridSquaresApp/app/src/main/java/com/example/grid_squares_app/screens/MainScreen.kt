package com.example.grid_squares_app.screens

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.grid_squares_app.R
import com.example.grid_squares_app.components.CustomButton
import com.example.grid_squares_app.components.EmptyState
import com.example.grid_squares_app.components.Feed
import com.example.grid_squares_app.ui.theme.BackgroundColor
import kotlinx.coroutines.launch

@Composable
fun <T: Any> rememberMutableStateListOf(vararg elements: T): SnapshotStateList<T> {
    // rememberSaveable - для сохранения состояния списка чисел при смене ориентацииa
    return rememberSaveable(saver = snapshotStateListSaver()) {
        elements.toList().toMutableStateList() // default value - пустой snapshotStateList<Int>
    }
}

private fun <T : Any> snapshotStateListSaver() = listSaver<SnapshotStateList<T>, T>(
    save = { stateList -> stateList.toList() },
    restore = { it.toMutableStateList() },
)

@Composable
fun MainScreen(context: Context) {

    val numbers = rememberMutableStateListOf<Int>()
    val coroutineScope = rememberCoroutineScope()
    val gridState = rememberLazyGridState()
    val configuration = LocalConfiguration.current

    var isAddingSquare by remember { mutableStateOf(false) }
    var isRemovingSquare by remember { mutableStateOf(false) }

    LaunchedEffect(isAddingSquare, isRemovingSquare, numbers.size) {
        if (isAddingSquare) {
            numbers.add(numbers.size + 1)
            isAddingSquare = false
        }
        if (isRemovingSquare) {
            if (numbers.size > 0) {
                numbers.removeAt(numbers.size - 1)
            }
            isRemovingSquare = false
        }
        if (numbers.isNotEmpty()) {
            // автоматический скролл вниз до последнего элемента
            coroutineScope.launch {
                gridState.scrollToItem(numbers.size - 1)
            }
        }
    }

    // определение количества колонок в зависимости от ориентации экрана
    val columns = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 3

    Column(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 10.dp)
            .fillMaxSize()
            .background(color = BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (numbers.isEmpty()) {
            EmptyState(modifier = Modifier.weight(1f))
        } else {
            Feed(
                context = context,
                numbers = numbers,
                modifier = Modifier.weight(1f),
                gridState = gridState,
                columns = columns,
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            CustomButton(
                onClick = {
                    isAddingSquare = true
                },
                text = stringResource(id = R.string.add_square),
            )
            CustomButton(
                onClick = {
                    isRemovingSquare = true
                },
                text = stringResource(id = R.string.remove_square),
            )
        }
    }
}