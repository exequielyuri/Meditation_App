package com.example.meditationapp.journal

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Journal() {

    // this list is just a test case
    val list = listOf(
        JournalEntry(
            day = 7,
            date = 24,
            month = 9,
            year = 2022,
            meditation = "Bee's Breath",
            durationMin = 5,
            durationSec = 50,
            content = "Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime mollitia, molestiae quas vel sint commodi repudiandae consequuntur voluptatum laborum numquam blanditiis harum quisquam eius sed odit fugiat iusto fuga praesentium optio, eaque rerum!"
        ),
        JournalEntry(
            day = 6,
            date = 23,
            month = 9,
            year = 2022,
            meditation = "Mantra",
            durationMin = 30,
            durationSec = 25,
            content = "Provident similique accusantium nemo autem. Veritatis obcaecati tenetur iure eius earum ut molestias architecto voluptate aliquam nihil, eveniet aliquid culpa officia aut! Impedit sit sunt quaerat, odit, tenetur error, harum nesciunt ipsum debitis quas aliquid."
        ),
        JournalEntry(
            day = 5,
            date = 20,
            month = 9,
            year = 2022,
            meditation = "Third eye",
            durationMin = 9,
            durationSec = 30,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
        JournalEntry(
            day = 4,
            date = 19,
            month = 9,
            year = 2022,
            meditation = "Triphasic Breathing",
            durationMin = 3,
            durationSec = 2,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
        JournalEntry(
            day = 3,
            date = 17,
            month = 9,
            year = 2022,
            meditation = "Bee's Breath",
            durationMin = 5,
            durationSec = 10,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
        JournalEntry(
            day = 2,
            date = 16,
            month = 9,
            year = 2022,
            meditation = "Om Chanting",
            durationMin = 0,
            durationSec = 0,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
        JournalEntry(
            day = 1,
            date = 13,
            month = 9,
            year = 2022,
            meditation = "Trataka",
            durationMin = 12,
            durationSec = 30,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
        JournalEntry(
            day = 7,
            date = 12,
            month = 9,
            year = 2022,
            meditation = "Candle",
            durationMin = 17,
            durationSec = 20,
            content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .padding(top = 0.dp)
    ) {
        JournalGraph(entries = list)
    }
}