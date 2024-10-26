package com.example.grid_squares_app.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grid_squares_app.R
import com.example.grid_squares_app.ui.theme.EmptyStateTextColor

@Composable
fun EmptyState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.padding(horizontal = 8.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.no_squares),
            fontSize = 16.sp,
            color = EmptyStateTextColor,
        )
    }
}