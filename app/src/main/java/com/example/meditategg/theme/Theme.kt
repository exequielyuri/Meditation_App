package com.example.meditategg.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    /*TODO*/
)

private val LightColorPalette = lightColors(
    primary = Purple50, // ui color
    onPrimary = Color.White, // select color
    primaryVariant = Purple60, // gradient with primary

    secondary = Purple10, // titles, content
    onSecondary = Purple40, // unselectColor
    secondaryVariant = Purple70, // purple shadow

    surface = Purple20, // white bg alternative
    onSurface = Purple30, // dividers, axes

    background = Color.White,
    onBackground = Purple45, // bars, nodes

    error = Color.LightGray,
    onError = Color.Black
)

@Composable
fun MeditateGGTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}