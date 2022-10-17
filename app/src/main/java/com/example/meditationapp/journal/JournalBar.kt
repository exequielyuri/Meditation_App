package com.example.meditationapp.journal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun JournalBar(
    modifier: Modifier = Modifier,
    width: Dp,
    height: Dp = 0.dp,
    color: Color
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                modifier = modifier
                    .width(width)
                    .height(height),
                shape = RoundedCornerShape(10),
                colors = ButtonDefaults.buttonColors(backgroundColor = color),
                contentPadding = PaddingValues(),
                onClick = { /*TODO*/ },
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 5.dp,
                    pressedElevation = 0.dp
                )
            ) {

            }
        }
}