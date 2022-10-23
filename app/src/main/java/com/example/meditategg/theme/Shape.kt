package com.example.meditategg.theme

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

val RoundedHexagon = GenericShape { size, _ ->
    val W = size.width
    val H = size.height

    val pSmall = 5f
    val pBig = 12f
    val round = 13f

    moveTo(W/2 - pBig, pSmall)
    quadraticBezierTo(
        x1 = W/2,
        y1 = 0f,
        x2 = W/2 + pBig,
        y2 = pSmall
    )
    lineTo(W - pBig, H/4 - pSmall)
    quadraticBezierTo(
        x1 = W,
        y1 = H/4,
        x2 = W,
        y2 = H/4 + round
    )
    lineTo(W, H*3/4 - round)
    quadraticBezierTo(
        x1 = W,
        y1 = H*3/4,
        x2 = W - pBig,
        y2 = H*3/4 + pSmall
    )
    lineTo(W/2 + pBig, H - pSmall)
    quadraticBezierTo(
        x1 = W/2,
        y1 = H,
        x2 = W/2 - pBig,
        y2 = H - pSmall
    )
    lineTo(0f + pBig, H*3/4 + pSmall)
    quadraticBezierTo(
        x1 = 0f,
        y1 = H*3/4,
        x2 = 0f,
        y2 = H*3/4 - round
    )
    lineTo(0f, H/4 + round)
    quadraticBezierTo(
        x1 = 0f,
        y1 = H/4,
        x2 = pBig,
        y2 = H/4 - pSmall
    )
}

val RoundedHexagonCutout = GenericShape { size, _ ->
    val W = size.width
    val H = size.height

    val pSmall = 5f
    val pBig = 12f
    val round = 13f
    val round2 = 20f

    moveTo(0f - round2, H/2)
    quadraticBezierTo(
        x1 = 0f,
        y1 = H/2,
        x2 = 0f,
        y2 = H/2 + round2
    )
    lineTo(0f, H*3/4 - round)
    quadraticBezierTo(
        x1 = 0f,
        y1 = H*3/4,
        x2 = pBig,
        y2 = H*3/4 + pSmall
    )
    lineTo(W/2 - pBig, H - pSmall)
    quadraticBezierTo(
        x1 = W/2,
        y1 = H,
        x2 = W/2 + pBig,
        y2 = H - pSmall
    )
    lineTo(W - pBig, H*3/4 + pSmall)
    quadraticBezierTo(
        x1 = W,
        y1 = H*3/4,
        x2 = W,
        y2 = H*3/4 - round
    )
    lineTo(W, H/2 + round2)
    quadraticBezierTo(
        x1 = W,
        y1 = H/2,
        x2 = W + round2,
        y2 = H/2
    )

}