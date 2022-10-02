package com.example.meditationapp.journal

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationapp.ui.theme.Purple
import com.example.meditationapp.ui.theme.PurpleLightLight

@Composable
fun JournalPages(
    entries: List<JournalEntry>,
    titleSize: TextUnit = 18.sp,
    contentSize: TextUnit = 16.sp,
    spaceBetweenEntry: Dp = 16.dp,
    contentIndent: Dp = 16.dp
) {
    LazyColumn(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 60.dp)
    ) {
        items(items = entries, itemContent = { item ->

            Divider(
                color = PurpleLightLight,
                thickness = 3.dp,
            )

            Spacer(modifier = Modifier.height(spaceBetweenEntry))
            Text(
                text = "${item.month}/${item.date}: ${item.meditation}",
                fontSize = titleSize
            )

            Text(
                text = item.content,
                fontSize = contentSize,
                modifier = Modifier.padding(start = contentIndent)
            )
            Spacer(modifier = Modifier.height(spaceBetweenEntry))
        })
    }
}