package com.example.meditationapp.journal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.meditationapp.ui.theme.PurpleLightLight
import com.example.meditationapp.ui.theme.Yellow20

@Composable
fun JournalBar(
    modifier: Modifier = Modifier,
    width: Dp = 25.dp,
    height: Dp = 0.dp,
    gradient: Brush,
    label: String = "",
    fontSize: TextUnit,
    fontColor: Color = PurpleLightLight
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                modifier = modifier
                    .width(width)
                    .height(height),
                shape = RoundedCornerShape(10),
                colors = ButtonDefaults.buttonColors(backgroundColor = Yellow20),
                contentPadding = PaddingValues(),
                onClick = { /*TODO*/ },
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp
                )
            ) {

            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = label,
                fontSize = fontSize,
                color = fontColor,
            )
        }
}