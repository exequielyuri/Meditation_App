package com.example.meditategg.common.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun GraphBar(
    modifier: Modifier = Modifier,
    width: Dp,
    totalDurationSec: Int = 0,
    maxDurationSec: Int = 0,
    maxDp: Int = 0,
    onBarClick: (Int) -> Unit = {},
) {
    val height = if (totalDurationSec==0) 0 else calculateHeight(totalDurationSec, maxDurationSec, maxDp)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = modifier
                .width(width)
                .height(height.dp),
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.onBackground),
            contentPadding = PaddingValues(),
            onClick = { onBarClick(height) },
            elevation = ButtonDefaults.elevation(
                defaultElevation = 5.dp,
                pressedElevation = 0.dp
            )
        ) {}
    }
}

private fun calculateHeight(
    totalDurationSec: Int,
    maxDurationSec: Int,
    maxDp: Int
): Int {
    return if (totalDurationSec>maxDurationSec) maxDp
    else (totalDurationSec.toFloat() / maxDurationSec * maxDp).roundToInt()
}