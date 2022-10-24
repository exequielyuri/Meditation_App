package com.example.meditategg.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditategg.theme.Roboto

@Composable
fun GradientButton(
    modifier: Modifier = Modifier,
    text: String = "",
    textColor: Color = Color.Black,
    fontSize: TextUnit = 16.sp,
    fontFamily: FontFamily = Roboto,
    fontWeight: FontWeight = FontWeight.Normal,
    style: TextStyle = TextStyle.Default,
    elevation: ButtonElevation = ButtonDefaults.elevation(),
    gradient: Brush,
    horizontalPad: Dp = 16.dp,
    verticalPad: Dp = 8.dp,
    width: Dp = 160.dp,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier.width(width),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        elevation = elevation,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .width(width)
                .background(brush = gradient)
                .padding(horizontal = horizontalPad, vertical = verticalPad),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = textColor,
                fontSize = fontSize,
                fontFamily = fontFamily,
                fontWeight = fontWeight,
                style = style
            )
        }
    }
}