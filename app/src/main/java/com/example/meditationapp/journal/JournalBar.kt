package com.example.meditationapp.journal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationapp.ui.theme.DarkYellowGradient
import com.example.meditationapp.ui.theme.LightYellowGradient
import com.example.meditationapp.ui.theme.PurpleDark
import com.example.meditationapp.ui.theme.PurpleLightLight
import java.util.*

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
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                contentPadding = PaddingValues(),
                onClick = { /*TODO*/ },
            ) {
                Box(modifier = Modifier.background(gradient).padding(100.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = label,
                fontSize = fontSize,
                color = fontColor,
            )
        }
}

//@Preview
//@Composable
//fun joorn() {
//    Row(
//        verticalAlignment = Alignment.Bottom
//    ) {
//        JournalBar(
//            width = 8.dp,
//            height = 20.dp,
//            label = "Mon",
//            fontSize = 4.sp
//        )
//        Spacer(Modifier.width(8.dp))
//        JournalBar(
//            width = 8.dp,
//            height = 25.dp,
//            label = "Tue",
//            fontSize = 5.sp
//        )
//        Spacer(Modifier.width(8.dp))
//        JournalBar(
//            width = 8.dp,
//            height = 15.dp,
//            label = "Wed",
//            fontSize = 6.sp
//        )
//        Spacer(Modifier.width(8.dp))
//        JournalBar(
//            width = 8.dp,
//            height = 0.dp,
//            label = "Thu",
//            fontSize = 7.sp
//        )
//    }
//}