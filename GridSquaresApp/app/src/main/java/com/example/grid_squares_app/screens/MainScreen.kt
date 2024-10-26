package com.example.grid_squares_app.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grid_squares_app.R
import com.example.grid_squares_app.components.EmptyState
import com.example.grid_squares_app.components.Feed
import com.example.grid_squares_app.ui.theme.BackgroundColor
import com.example.grid_squares_app.ui.theme.BtnColor
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {

    // rememberSaveable - для сохранения состояния списка чисел при смене ориентации
    var numbers: List<Int> by rememberSaveable { mutableStateOf(listOf()) }
    val gridState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current

    // автоматический скролл вниз до последнего элемента
    LaunchedEffect(numbers.size) {
        if (numbers.isNotEmpty()) {
            coroutineScope.launch {
                gridState.scrollToItem(numbers.size - 1)
            }
        }
    }

    // определение количества колонок в зависимости от ориентации экрана
    val columns = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 3

    Column(
        modifier = Modifier.fillMaxSize().background(color = BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (numbers.isEmpty()) {
            EmptyState(modifier = Modifier.weight(1f))
        } else {
            Feed(
                numbers = numbers,
                modifier = Modifier.weight(1f),
                gridState = gridState,
                columns = columns,
            )
        }

        Button(
            onClick = { numbers += numbers.size + 1 },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BtnColor),
        ) {
            Text(
                text = stringResource(id = R.string.add_square),
                fontSize = 16.sp,
            )
        }
    }
}