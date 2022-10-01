package com.example.meditationapp.journal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationapp.ui.theme.Gold
import com.example.meditationapp.ui.theme.PurpleLightLight
import kotlin.math.roundToInt
import com.example.meditationapp.R

@Composable
fun JournalGraph(
    entries: List<JournalEntry>,
    graphBarColor: Color = Gold,
    fontColor: Color = PurpleLightLight
) {

    Box {
        Image(
            painter = painterResource(R.drawable.journal_graph),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
        )
        LazyRow(
            verticalAlignment = Alignment.Bottom,
            reverseLayout = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .padding(horizontal = 20.dp, vertical = 40.dp)
        ) {
            val max = 30 * 60 // 30 minutes is max (for now), above 30 mins, just make it full
            val maxDp = 100
            val spaceBetweenBar = 15.dp

            items(items = entries, itemContent = { item ->

                val day = when(item.day) {
                    1 -> "Sun"
                    2 -> "Mon"
                    3 -> "Tue"
                    4 -> "Wed"
                    5 -> "Thu"
                    6 -> "Fri"
                    else -> "Sat"
                }

                val seconds = item.durationMin * 60 + item.durationSec
                val height = if (seconds>30*60) maxDp.dp else (seconds.toFloat() / max * maxDp).roundToInt().dp

                Spacer(modifier = Modifier.width(spaceBetweenBar))
                JournalBar(
                    height = height,
                    color = graphBarColor,
                    label = day,
                    fontSize = 16.sp,
                    fontColor = fontColor
                )
                Spacer(modifier = Modifier.width(spaceBetweenBar))
            })

        }
    }
}