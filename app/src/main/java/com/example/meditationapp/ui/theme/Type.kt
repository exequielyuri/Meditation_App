package com.example.meditationapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.meditationapp.R

val Roboto = FontFamily(
    fonts = listOf(
        Font(R.font.roboto_regular, FontWeight.Normal),
        Font(R.font.roboto_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.roboto_black, FontWeight.Black),
        Font(R.font.roboto_blackitalic, FontWeight.Black, FontStyle.Italic),
        Font(R.font.roboto_bold, FontWeight.Bold),
        Font(R.font.roboto_bolditalic, FontWeight.Bold, FontStyle.Italic),
        Font(R.font.roboto_medium, FontWeight.Medium),
    )

)

val Lora = FontFamily(
    fonts = listOf(
        Font(R.font.lora_regular, FontWeight.Normal),
        Font(R.font.lora_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.lora_bold, FontWeight.Bold),
        Font(R.font.lora_bolditalic, FontWeight.Bold, FontStyle.Italic),
        Font(R.font.lora_medium, FontWeight.Medium),
        Font(R.font.lora_mediumitalic, FontWeight.Medium, FontStyle.Italic),
        Font(R.font.lora_semibold, FontWeight.SemiBold),
        Font(R.font.lora_semibolditalic, FontWeight.SemiBold, FontStyle.Italic)
    )
)

val Inter = FontFamily(
    fonts = listOf(
        Font(R.font.inter_regular, FontWeight.Normal),
        Font(R.font.inter_light, FontWeight.Light),
        Font(R.font.inter_extralight, FontWeight.ExtraLight),
        Font(R.font.inter_black, FontWeight.Black),
        Font(R.font.inter_medium, FontWeight.Medium),
        Font(R.font.inter_bold, FontWeight.Bold),
        Font(R.font.lora_semibold, FontWeight.SemiBold),
        Font(R.font.inter_extrabold, FontWeight.ExtraBold),
        Font(R.font.inter_thin, FontWeight.Thin)
    )
)


// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)