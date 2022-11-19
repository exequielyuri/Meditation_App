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
    object Posture : Meditation(
        name = "Posture",
        instructions = listOf(
            Instruction(
                "Comfort is important",
                "It is important to adapt a good posture while meditating, so that your body won't distract you by giving you the urge to move."
            ),
            Instruction(
                "Straighten your spine",
                "You can be sitting down, standing up, or laying down, as long as your spine is straight. This is what is comfortable for your body and it will help you focus for long periods of time."
            ),
            Instruction(
                "Make it easier with physics",
                "You can keep your spine effortlessly straight, by putting your hips higher than your knees. You can sit on a pillow or sit in the tip of a chair. This produces a center of gravity that makes you naturally want to sit up straight. Notice that in doing so, you won't be needing to exert muscular effort to keep the posture."
            ),
            Instruction(
                "Use known techniques",
                "The normal indian sit actually places your knees higher than the hips, which makes it harder to sit up straight. This led experienced meditators to discover postures such as the: Ardha Padmasana (half lotus), Padmasana (full lotus), or Vajrasana (thunderbolt)."
            )
        )
    )

    object RotatingAwareness : Meditation(
        name = "Rotating Awareness",
        instructions = listOf(
            Instruction(
                "Find noisy environment",
                "Go to a crowded place with varied sounds - like a cafeteria or a shopping center, or park. The goal in this meditation is to calm a very active mind, and can be used as a preparation for other practices."
            ),
            Instruction(
                "Notice the sounds",
                "Close your eyes and notice the sounds around you. Try to notice one sound at a time."
            ),
            Instruction(
                "Rotate your awareness",
                "As quickly as you can, move to the next sound. Don't try to attend to the sound for a prolonged period of time or interpret it. So if you hear someone speaking, don't pay attention to the words. Avoid trying to remember which sounds you've heard and just keep shifting your attention to the next sound for 3-5 minutes."
            ),
            Instruction(
                "Notice the peace",
                "After the rapid rotation, your mind will naturally be exhausted. This is the time where your mind is in a calm state, and you can now transition to another meditation technique."
            ),
            Instruction(
                "Try other senses",
                "The secret in this technique is not actually in the sound, but in the rotation of your awareness. So if you can't find a noisy place, you can use your vision to look at objects in rapid succession (waves in the ocean, leaves on a tree) or maybe attending to physical sensations (each heartbeat, or muscle contracting/relaxing)."
            )
        )
    )

    object Kapalbhati : Meditation(
        name = "Kapalbhati",
        instructions = listOf(
            Instruction(
                "Abnominal Breathing",
                "Start by sitting up straight, and then put one of your hand in your belly. When you inhale, let the belly expand, and when you exhale, contract the abdomen."
            ),
            Instruction(
                "Exhale forcefully",
                "Once you've practiced abnominal breathing, the next thing to do is forceful exhalation by contracting the abdomen. Forcefully exhale out of your nose at the rate of 1 exhalation per second. Notice that you passively inhale a bit of air every time you forcefully exhale."
            ),
            Instruction(
                "Go for higher reps",
                "If you're just starting out, you may want to start with 9 rounds of forceful breaths, and then letting yourself rest afterwards. After resting for a minute, or when you feel ready, do 12 rounds of forceful breaths. Do this until you reach 33 breaths."
            ),
            Instruction(
                "Notice what you feel",
                "Whenever you are at a resting phase, notice that your breathing slowed down. Sit in that state for a while, and realize how peaceful it is. This practice is useful when your mind is in an anxious state."
            )
        )
    )

    object WhatIsSelf : Meditation(
        name = "What is Self?",
        instructions = listOf(
            Instruction(
                "Meditated State",
                "Before doing this meditation practice, it is recommended that you are in a state of peace and calmness. This can be done, after doing another meditation technique."
            ),
            Instruction(
                "Find your identity",
                "Try to find the aspects of your identity within you. These could be attributes like: a failure, smart, stupid, unloveable, your gender, lazy, brilliant, etc."
            ),
            Instruction(
                "Pick an attribute",
                "Pick one attribute of your identity, and then find that attribute within you. Where does that attribute come from? Can you feel it? For example, if you identify as a man, try to find, where the \"man-ness\" is within you. If your only experience of life is this quiet moment of meditation, would you still be a man?"
            ),
            Instruction(
                "Watch out for thoughts",
                "In determining where those attributes come from, be careful that you answer may be coming from your thoughts. For example, you may think \"I am a man, because I can feel my genitals\", but is that really the reason? For those people who needed to remove their genitals, for medical reasons, are they still considered a man or a woman? See for yourself whether these identities are real or not. You may be quite surprised that these things don't exist. They are thoughts, abstractions, or constructs of the mind."
            )
        )
    )

    object FollowingBreath : Meditation(
        name = "Following Breath",
        instructions = listOf(
            Instruction(
                "Observe your breathing",
                "In this meditation practice, you will focus on your breath. Detecting the slightest flow of your breath trains your awareness."
            ),
            Instruction(
                "Notice the temperature",
                "While breathing, notice the temperature of your breath, and ask yourself what the temperature of the air is. Is it cold? Is it warm? Does it vary during inhilation and exhalation?"
            ),
            Instruction(
                "See where it goes",
                "Now that you have found the feeling of breath, now see where it goes. Scan the different parts of your body. Pay attention to: the tip of your nose, nasal passages, back of the throat, and down into the lungs."
            ),
            Instruction(
                "Look for unusual places",
                "After being able to track the common places where you can feel your breath, you can now proceed and try to look further. Try to follow the sensations of cold or warmth, and see if you can also feel it in places that you never expected."
            )
        )
    )

    object RewindPractice : Meditation(
        name = "Rewind Practice",
        instructions = listOf(
            Instruction(
                "Recall your day",
                "The first part of this practice involves going through your day in reverse order and considering emotions that were evoked by the experiences throughout your day. This practice is often done to help yourself sleep better."
            ),
            Instruction(
                "Start in the present",
                "Notice what you are doing and what you are feeling, and you can start with \"I am lying down in my bed and starting the meditative rewind practice\"."
            ),
            Instruction(
                "Do it in reverse",
                "Remember each action you took and teh emotions that were evoked and then mark the experience of emotion but don't get caught up in it. For example: \"Before bed, I brushed my teeth.\", \"Before that, I changed my clothes.\", \"Before that, I emptied a load of laundry.\", \"As I emptied the load of laundry I felt frustrated that I was out of pajamas.\", \"I noticed my exhaustion after a long week.\", and so on."
            ),
            Instruction(
                "Count from 100 to 1",
                "After recalling your day for maybe 15 to 20 minutes, you can now move on to the second part of the practice, which is to count from 100 to 1. "
            ),
            Instruction(
                "Don't wander",
                "While you're counting downwards, don't let your mind wander. You will be using the numbers as a hook for your mind to stay at one particular thing, just like how people count sheeps for them to fall asleep."
            ),
            Instruction(
                "Find the sweet spot",
                "If you count very fast, the practice won't be boring, and won't help you sleep; but if you count very slowly your mind will end up wandering. The timing would vary from person to person, but you can start by counting down after 5 seconds and then make adjustments from there. So what happens now if you were able to count down until the last number and you're still awake? Then you're probably doing the practice wrong (haha), and may need to try again (sorry)."
            )
        )
    )

    // below meditations are not yet "cleaned"

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
    object Ice : Meditation(
        name = "Ice"
    )

    object Custom : Meditation(
        name = "Custom"
    )
}
