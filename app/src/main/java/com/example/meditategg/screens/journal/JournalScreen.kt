package com.example.meditategg.screens.journal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditategg.common.composable.BottomShadow
import com.example.meditategg.common.composable.GraphBar
import com.example.meditategg.common.composable.PageCard
import com.example.meditategg.theme.*

@Composable
fun JournalScreen(
    modifier: Modifier = Modifier,
    viewModel: JournalViewModel = hiltViewModel(),
    graphItemWidth: Dp = 25.dp
) {
    val entries = viewModel.entries

    Column(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.onPrimary),
            horizontalAlignment = Alignment.Start
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    modifier = modifier.padding(top = 10.dp, bottom = 5.dp),
                    text = "Recent Meditations",
                    color = MaterialTheme.colors.primary,
                    fontSize = 24.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Bold
                )

                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    MaterialTheme.colors.background,
                                    MaterialTheme.colors.surface
                                ),
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ),

                    ) {
                    Spacer(modifier = modifier.height(15.dp))
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        if (entries.isNotEmpty()) {
                            var latestDay = entries.first().dayOfWeek-1
                            for (curr in latestDay downTo 0) {
                                GraphBar(
                                    width = graphItemWidth,
                                    totalDurationSec = entries.elementAt(curr).durationMin * 60 + entries.elementAt(curr).durationSec,
                                    maxDurationSec = 1800,
                                    maxDp = 100,
                                    onBarClick = viewModel::onBarClick
                                )
                            }
                            latestDay++
                            for (curr in latestDay..6) { GraphBar(width = graphItemWidth) }
                        }
                    }
                }


                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 13.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val daysInWeek = listOf("S", "M", "T", "W", "T", "F", "S")

                    daysInWeek.forEach() { day ->
                        Text(
                            modifier = modifier.width(graphItemWidth),
                            text = day,
                            color = MaterialTheme.colors.onBackground,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Roboto,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        Box(modifier = Modifier.background(MaterialTheme.colors.surface)) {
            BottomShadow()
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) { items(items = entries, itemContent = { item -> PageCard(item, viewModel::onBookmarkClick) }) }
        }
    }

    DisposableEffect(viewModel) {
        viewModel.addListener()
        onDispose { viewModel.removeListener() }
    }
}