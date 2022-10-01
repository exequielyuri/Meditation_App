package com.example.meditationapp.journal

import com.example.meditationapp.meditation.Meditation

class JournalEntry(
    val day: Int,
    val date: Int,
    val month: Int,
    val year: Int,
    val meditation: String, // change later to "meditation: Meditation"
    val durationMin: Int,
    val durationSec: Int,
    val content: String
    ) {
}

/* example of journal of a user
listOf(
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
        durationMin = 3,
        durationSec = 25,
        content = "Provident similique accusantium nemo autem. Veritatis obcaecati tenetur iure eius earum ut molestias architecto voluptate aliquam nihil, eveniet aliquid culpa officia aut! Impedit sit sunt quaerat, odit, tenetur error, harum nesciunt ipsum debitis quas aliquid."
    ),
    JournalEntry(
        day = 3,
        date = 20,
        month = 9,
        year = 2022,
        meditation = "Third eye",
        durationMin = 9,
        durationSec = 30,
        content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
    ),
    JournalEntry(
        day = 2,
        date = 19,
        month = 9,
        year = 2022,
        meditation = "Triphasic Breathing",
        durationMin = 3,
        durationSec = 2,
        content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
    ),
    JournalEntry(
        day = 7,
        date = 17,
        month = 9,
        year = 2022,
        meditation = "Bee's Breath",
        durationMin = 5,
        durationSec = 10,
        content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
    ),
    JournalEntry(
        day = 6,
        date = 16,
        month = 9,
        year = 2022,
        meditation = "Om Chanting",
        durationMin = 24,
        durationSec = 30,
        content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
    ),
    JournalEntry(
        day = 3,
        date = 13,
        month = 9,
        year = 2022,
        meditation = "Trataka",
        durationMin = 12,
        durationSec = 30,
        content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
    ),
    JournalEntry(
        day = 2,
        date = 12,
        month = 9,
        year = 2022,
        meditation = "Candle",
        durationMin = 17,
        durationSec = 20,
        content = "Reprehenderit, quia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis modi at sunt excepturi expedita sint?"
    ),
)


*/