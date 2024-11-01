package com.example.grid_squares_app.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grid_squares_app.ui.theme.BlueColor
import com.example.grid_squares_app.ui.theme.RedColor

fun changeBtnColor(currentColor: Color) : Color {
    return if (currentColor == RedColor) BlueColor else RedColor
}

@Composable
fun Square(context: Context, number: Int, backgroundColor: Color) {
    var changeColor by rememberSaveable { mutableStateOf(false) }
    var currentColor by remember {
        mutableStateOf(backgroundColor)
    }

    LaunchedEffect(changeColor) {
        if (changeColor)
        {
            currentColor = changeBtnColor(currentColor)
            changeColor = false
        }
    }

    Button(
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        onClick = {
            val toast = Toast.makeText(context, number.toString(), Toast.LENGTH_SHORT)
            toast.show()
            changeColor = true
        },
        modifier = Modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(currentColor),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = number.toString(),
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }
}