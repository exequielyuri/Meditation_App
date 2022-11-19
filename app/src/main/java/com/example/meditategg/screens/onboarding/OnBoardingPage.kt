package com.example.meditategg.screens.onboarding

import androidx.annotation.DrawableRes
import com.example.meditategg.R.drawable as AppIcon

sealed class OnBoardingPage(
    @DrawableRes
    val icon: Int,
    val header: String,
    val description: String
) {
    object Variety : OnBoardingPage(
        icon = AppIcon.variety,
        header = "Various Meditations",
        description = "Each of us have their own temperament, and for meditation to work, we would need to find a technique specifically suited for you. There are 4 categories of meditation: Focus, Open Awareness, Grounding, and Inquisitive."
    )

    object Focus : OnBoardingPage(
        icon = AppIcon.focus,
        header = "Focus",
        description = "This type of practice is about telling our mind to put all of our attention towards a single point. Are you someone who is driven to focus on one thing?"
    )

    object OpenAwareness : OnBoardingPage(
        icon = AppIcon.open_awareness,
        header = "Open Awareness",
        description = "Opposite to focusing, this is about letting our mind observe and notice the signals that it's getting. Are you someone who likes to chill and watch what your mind does?"
    )

    object Grounding : OnBoardingPage(
        icon = AppIcon.grounding,
        header = "Grounding",
        description = "The goal of this type of meditation is to empty our mind and gravitate to the present. Are you someone who wants to get away from unwanted thoughts?"
    )

    object Inquisitive : OnBoardingPage(
        icon = AppIcon.inquisitive,
        header = "Inquisitive",
        description = "As opposed to grounding, in this practice, we want to activate our mind and let it lead us in exploring ideas. Are you someone who needs to give your mind something to be curious about?"
    )

    object Graph : OnBoardingPage(
        icon = AppIcon.meditation_map_graph,
        header = "Meditation Map",
        description = "The different meditation techniques are layed out in a graph where the X-axis is the amount of control you need for the mind, and the Y-axis refers to how much energy you give to your mind."
    )
}
