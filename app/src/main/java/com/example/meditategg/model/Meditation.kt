package com.example.meditategg.model

sealed class Meditation(
    val name: String,
    val instructions: List<Instruction> = listOf(
        Instruction(
            header = "",
            body = ""
        )
    ),
    val science: String = "",
    val spiritual: String = ""
) {
    object Trataka : Meditation(
        name = "Trataka"
    )

    object BeesBreath : Meditation(
        name = "Bee's Breath"
    )

    object Mindfulness : Meditation(
        name = "Mindfulness"
    )

    object ThirdEye : Meditation(
        name = "Third Eye",
        instructions = listOf(
            Instruction(
                "Sit up straight",
                "Sit up straight and relax. Breathe deeply, 3 times. Notice your surrounding for a bit, and when you're ready, close your eyes."
            ),
            Instruction(
                "Hover finger on forehead",
                "Take your middle finger and hover it in the middle of your eyebrows. Place it as close as you can without touching your skin. You might feel a tingling sensation especially during the first iteration."
            ),
            Instruction(
                "Focus on the sensation",
                "While you're hovering your finger, you will notice a sensation between your eyebrows. If you are having trouble feeling the spot, you can move around your middle finger, through the different parts of your forehead; moving in and out of the spot."
            ),
            Instruction(
                "Bring down your hands",
                "After a minute of hovering, bring your hands down, while still focusing on the spot. Focus on that sensation and try to imagine that your attention is a ray of light that is pointing to that spot."
            ),
            Instruction(
                "Repeat as necessary",
                "If your focus is starting to fade away, you can bring your middle finger back and notice the sensation. Keep doing this for as long as you can."
            ),
            Instruction(
                "Slowly exhale and come back",
                "When you're done, breathe deeply and exhale slowly. Notice how you feel, and if you enjoyed the experience, try to practice gratitude towards the universe for giving you this chance. When you're ready, go ahead and open your eyes."
            )
        ),
        science = "If sensations come out in meditation, let them come out. That is how it heals you. When stuff gets suppressed, it comes out in maladaptive ways. Sometimes when things are buried, they start to come out in meditation.",
        spiritual = "People think that meditation is only about calming your mind, but actually it's about experiencing wonderful and amazing things. It's about feeling sensations that you would not feel otherwise. It is about cultivating inner experience."
    )

    // maybe remove these meditations later
    object Posture : Meditation(
        name = "Posture"
    )

    object Ice : Meditation(
        name = "Ice"
    )

    object Custom : Meditation(
        name = "Custom"
    )
}
