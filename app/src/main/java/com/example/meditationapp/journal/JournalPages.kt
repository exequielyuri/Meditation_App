package com.example.meditationapp.journal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationapp.ui.theme.*

@Composable
fun JournalPages(
    modifier: Modifier = Modifier,
    entries: List<JournalEntry>,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxWidth()
            .background(Purple20)
    ) { items(items = entries, itemContent = { item -> PageCard(entry = item) }) }
}

@Composable
fun PageCard (entry: JournalEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(horizontal = 15.dp)
            .padding(top = 18.dp)
            .clickable { },
        elevation = 3.dp,
        shape = MaterialTheme.shapes.small.copy(CornerSize(8.dp))
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
                    text = entry.meditation,
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