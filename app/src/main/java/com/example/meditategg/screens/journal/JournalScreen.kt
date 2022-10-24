package com.example.meditategg.screens.journal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditategg.common.composable.BottomShadow
import com.example.meditategg.common.composable.GraphBar
import com.example.meditategg.model.JournalEntry
import com.example.meditategg.theme.*
import kotlin.math.roundToInt

@Composable
fun Journal() {

    // this list is just a test case
    val list = listOf(
        JournalEntry(
            day = 7,
            date = 24,
            month = 9,
            year = 2022,
            meditationName = "Bee's Breath",
            durationMin = 5,
            durationSec = 50,
            content = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime mollitia, molestiae quas vel sint commodi repudiandae consequuntur voluptatum laborum numquam blanditiis harum quisquam eius sed odit fugiat iusto fuga praesentium optio, eaque rerum!"
        ),
        JournalEntry(
            day = 6,
            date = 23,
            month = 9,
            year = 2022,
            meditationName = "Mantra",
            durationMin = 30,
            durationSec = 25,
            content = "Provident similique accusantium nemo autem. Veritatis obcaecati tenetur iure eius earum ut molestias architecto voluptate aliquam nihil, eveniet aliquid culpa officia aut! Impedit sit sunt quaerat, odit, tenetur error, harum nesciunt ipsum debitis quas aliquid."
        ),
        JournalEntry(
            day = 5,
            date = 20,
            month = 9,
            year = 2022,
            meditationName = "Third eye",
            durationMin = 9,
            durationSec = 30,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
        JournalEntry(
            day = 4,
            date = 19,
            month = 9,
            year = 2022,
            meditationName = "Triphasic Breathing",
            durationMin = 3,
            durationSec = 2,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
        JournalEntry(
            day = 3,
            date = 17,
            month = 9,
            year = 2022,
            meditationName = "Bee's Breath",
            durationMin = 5,
            durationSec = 10,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
        JournalEntry(
            day = 2,
            date = 16,
            month = 9,
            year = 2022,
            meditationName = "Om Chanting",
            durationMin = 0,
            durationSec = 0,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
        JournalEntry(
            day = 1,
            date = 13,
            month = 9,
            year = 2022,
            meditationName = "Trataka",
            durationMin = 12,
            durationSec = 30,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
        JournalEntry(
            day = 7,
            date = 12,
            month = 9,
            year = 2022,
            meditationName = "Candle",
            durationMin = 17,
            durationSec = 20,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
        JournalEntry(
            day = 6,
            date = 23,
            month = 9,
            year = 2022,
            meditationName = "Mantra",
            durationMin = 30,
            durationSec = 25,
            content = "Provident similique accusantium nemo autem. Veritatis obcaecati tenetur iure eius earum ut molestias architecto voluptate aliquam nihil, eveniet aliquid culpa officia aut! Impedit sit sunt quaerat, odit, tenetur error, harum nesciunt ipsum debitis quas aliquid."
        ),
        JournalEntry(
            day = 5,
            date = 20,
            month = 9,
            year = 2022,
            meditationName = "Third eye",
            durationMin = 9,
            durationSec = 30,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
        JournalEntry(
            day = 4,
            date = 19,
            month = 9,
            year = 2022,
            meditationName = "Triphasic Breathing",
            durationMin = 3,
            durationSec = 2,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
        JournalEntry(
            day = 3,
            date = 17,
            month = 9,
            year = 2022,
            meditationName = "Bee's Breath",
            durationMin = 5,
            durationSec = 10,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
        JournalEntry(
            day = 2,
            date = 16,
            month = 9,
            year = 2022,
            meditationName = "Om Chanting",
            durationMin = 0,
            durationSec = 0,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
        JournalEntry(
            day = 1,
            date = 13,
            month = 9,
            year = 2022,
            meditationName = "Trataka",
            durationMin = 12,
            durationSec = 30,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
        JournalEntry(
            day = 7,
            date = 12,
            month = 9,
            year = 2022,
            meditationName = "Candle",
            durationMin = 17,
            durationSec = 20,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
        JournalEntry(
            day = 6,
            date = 23,
            month = 9,
            year = 2022,
            meditationName = "Mantra",
            durationMin = 30,
            durationSec = 25,
            content = "Provident similique accusantium nemo autem. Veritatis obcaecati tenetur iure eius earum ut molestias architecto voluptate aliquam nihil, eveniet aliquid culpa officia aut! Impedit sit sunt quaerat, odit, tenetur error, harum nesciunt ipsum debitis quas aliquid."
        ),
        JournalEntry(
            day = 5,
            date = 20,
            month = 9,
            year = 2022,
            meditationName = "Third eye",
            durationMin = 9,
            durationSec = 30,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
        JournalEntry(
            day = 4,
            date = 19,
            month = 9,
            year = 2022,
            meditationName = "Triphasic Breathing",
            durationMin = 3,
            durationSec = 2,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        JournalGraph(entries = list)
        JournalPages(entries = list)
    }
}

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

@Composable
fun JournalPages(
    modifier: Modifier = Modifier,
    entries: List<JournalEntry>,
    backgroundColor: Color = Purple20
) {
    Box(
        modifier = Modifier.background(backgroundColor)
    ) {
        BottomShadow()
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
        ) { items(items = entries, itemContent = { item -> PageCard(entry = item) }) }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PageCard(entry: JournalEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(horizontal = 15.dp)
            .padding(top = 18.dp),
        shape = MaterialTheme.shapes.small.copy(CornerSize(8.dp)),
        onClick = {},
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .width(70.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val month = when(entry.month) {
                    1 -> "JAN"
                    2 -> "FEB"
                    3 -> "MAR"
                    4 -> "APR"
                    5 -> "MAY"
                    6 -> "JUN"
                    7 -> "JUL"
                    8 -> "AUG"
                    9 -> "SEP"
                    10 -> "OCT"
                    11 -> "NOV"
                    else -> "DEC"
                }

                Text(
                    text = month,
                    fontSize = 18.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Medium,
                    color = Purple10
                )
                Text(
                    text = "${entry.date}",
                    fontSize = 30.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                    color = Purple10
                )
            }

            Divider(
                color = Purple30,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp)
                ,
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 15.dp, end = 20.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = entry.meditationName,
                    fontSize = 20.sp,
                    fontFamily = Lora,
                    fontWeight = FontWeight.SemiBold,
                    color = Purple50
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = entry.content,
                    fontSize = 16.sp,
                    fontFamily = Lora,
                    fontWeight = FontWeight.Normal,
                    color = Purple10,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
        }
    }
}