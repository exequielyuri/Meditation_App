package com.example.meditategg.screens.journal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditategg.common.composable.GraphBar
import com.example.meditategg.model.JournalEntry
import kotlin.math.roundToInt
import com.example.meditategg.theme.*

@Composable
fun JournalGraph(
    modifier: Modifier = Modifier,
    entries: List<JournalEntry>,
    fontColor: Color = Purple45,
    horizontalPad: Dp = 15.dp,
    itemWidth: Dp = 25.dp,
    backgroundColor: Color = Color.White
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 5.dp)
                .padding(horizontal = horizontalPad),
            text = "Recent Meditations",
            color = Purple50,
            fontSize = 24.sp,
            fontFamily = Roboto,
            fontWeight = FontWeight.Bold
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPad)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.White,
                            Purple20
                        ),

                    ),
                    shape = RoundedCornerShape(10.dp)
                ),

        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                val max = 30 * 60 // 30 minutes is max (for now), above 30 mins, just make it full
                val maxDp = 100

                val latestDay = entries.first().day

                for (curr in latestDay downTo 1) {
                    val seconds = entries.elementAt(curr).durationMin * 60 + entries.elementAt(curr).durationSec
                    val height = if (seconds>30*60) maxDp.dp else (seconds.toFloat() / max * maxDp).roundToInt().dp

                    GraphBar(
                        width = itemWidth,
                        height = height,
                        color = fontColor,
                    )
                }
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 13.dp)
                .padding(horizontal = horizontalPad),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val daysInWeek = listOf("S", "M", "T", "W", "T", "F", "S")

            daysInWeek.forEach() { day ->
                Text(
                    modifier = Modifier.width(itemWidth),
                    text = day,
                    color = fontColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Roboto,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}