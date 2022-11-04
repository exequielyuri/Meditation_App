package com.example.meditategg.common.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

@Composable
fun FacebookButton(
    onFacebookClick: () -> Unit,
    text: String = "",
) {
    GradientButton(
        text = text,
        textColor = MaterialTheme.colors.onPrimary,
        fontSize = 16.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.Medium,
        style = MaterialTheme.typography.h4.copy(
            shadow = Shadow(
                color = MaterialTheme.colors.primary,
                offset = Offset(0f, 1f),
                blurRadius = 1f
            )
        ),
        gradient = Brush.horizontalGradient(
            listOf(
                Color(0xff3B5998),
                Color(0xff7780D4)
            )
        ),
        width = 300.dp,
        horizontalPad = 50.dp,
        verticalPad = 10.dp,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 4.dp,
            pressedElevation = 0.dp
        ),
        onClick = onFacebookClick
    )
}

@Composable
fun GoogleButton(
    onGoogleClick: () -> Unit,
    text: String = "",
) {
    GradientButton(
        text = text,
        textColor = MaterialTheme.colors.onPrimary,
        fontSize = 16.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.Medium,
        style = MaterialTheme.typography.h4.copy(
            shadow = Shadow(
                color = MaterialTheme.colors.primary,
                offset = Offset(0f, 1f),
                blurRadius = 1f
            )
        ),
        gradient = Brush.horizontalGradient(
            listOf(
                Color(0xffDA1B60),
                Color(0xffD87412)
            )
        ),
        width = 300.dp,
        horizontalPad = 50.dp,
        verticalPad = 10.dp,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 4.dp,
            pressedElevation = 0.dp
        ),
        onClick = onGoogleClick
    )
}

@Composable
fun BasicButton(@StringRes text: Int, modifier: Modifier, action: () -> Unit) {
    Button(
        onClick = action,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        )
    ) {
        Text(text = stringResource(text), fontSize = 16.sp)
    }
}

@Composable
fun StatisticButton(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int? = null,
    iconColor: Color = MaterialTheme.colors.onPrimary,
    header: String = "",
    headerSize: TextUnit = 14.sp,
    label: String = "",
    labelSize: TextUnit = 12.sp,
    textColor: Color = MaterialTheme.colors.onPrimary,
    fontFamily: FontFamily = Roboto,
    style: TextStyle = MaterialTheme.typography.h4.copy(
        shadow = Shadow(
            color = MaterialTheme.colors.primary,
            offset = Offset(0f, 1f),
            blurRadius = 1f
        )
    ),
    elevation: ButtonElevation = ButtonDefaults.elevation(
        defaultElevation = 4.dp,
        pressedElevation = 0.dp
    ),
    gradient: Brush = Brush.verticalGradient(listOf(
            MaterialTheme.colors.primaryVariant,
            MaterialTheme.colors.primary
    )),
    horizontalPad: Dp = 15.dp,
    verticalPad: Dp = 1.dp,
    height: Dp = 85.dp,
    width: Dp = 80.dp,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier
            .defaultMinSize(minWidth = width)
            .height(height),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        elevation = elevation,
        shape = RoundedCornerShape(25),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .background(brush = gradient)
                .padding(horizontal = horizontalPad, vertical = verticalPad)
                .defaultMinSize(minWidth = width)
                .height(height),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            if (icon != null) {
                Icon(
                    painter = painterResource(icon),
                    modifier = Modifier.weight(4f),
                    contentDescription = null,
                    tint = iconColor
                )
            }

            Column(
                modifier = Modifier.weight(4f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = header,
                    color = textColor,
                    fontSize = headerSize,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    style = style,
                    textAlign = TextAlign.Center
                )
                
                if(icon == null) {
                    Spacer(modifier = Modifier.weight(1f))
                }

                Text(
                    text = label,
                    color = textColor,
                    fontSize = labelSize,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}