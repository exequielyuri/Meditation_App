package com.example.meditategg.screens.map

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import com.example.meditategg.MEDITATION_SCREEN
import com.example.meditategg.model.Meditation
import com.example.meditategg.model.service.LogService
import com.example.meditategg.screens.MeditateGGViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    logService: LogService,
) : MeditateGGViewModel(logService) {
    var uiState = mutableStateOf(MapUiState())
        private set

    val constraints = ConstraintSet {
        val posture = createRefFor(Meditation.Posture.name)
        val rotating_awareness = createRefFor(Meditation.RotatingAwareness.name)
        val kapalbhati = createRefFor(Meditation.Kapalbhati.name)
        val what_is_self = createRefFor(Meditation.WhatIsSelf.name)
        val following_breath = createRefFor(Meditation.FollowingBreath.name)
        val rewind_practice = createRefFor(Meditation.RewindPractice.name)

        constrain(posture) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(rotating_awareness) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start, 120.dp)
            end.linkTo(parent.end)
        }

        constrain(kapalbhati) {
            top.linkTo(parent.top, 135.dp)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(what_is_self) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom, 170.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end, 100.dp)
        }

        constrain(following_breath) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom, 140.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(rewind_practice) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom, 100.dp)
            start.linkTo(parent.start, 150.dp)
            end.linkTo(parent.end)
        }

//        constrain(rotating_awareness) {
//            top.linkTo(parent.top)
//            bottom.linkTo(parent.bottom)
//            start.linkTo(posture.end, 200.dp)
//        }
//
//        constrain(kapalbhati) {
//            top.linkTo(posture.top, 200.dp)
//            start.linkTo(parent.start)
//            end.linkTo(parent.end)
//        }
//
//        constrain(what_is_self) {
//            top.linkTo(parent.top)
//            bottom.linkTo(parent.bottom, 200.dp)
//            end.linkTo(posture.start, 200.dp)
//        }
//
//        constrain(following_breath) {
//            bottom.linkTo(posture.top, 200.dp)
//            start.linkTo(parent.start)
//            end.linkTo(parent.end)
//        }
//
//        constrain(rewind_practice) {
//            bottom.linkTo(rotating_awareness.top, 200.dp)
//            start.linkTo(following_breath.end, 200.dp)
//        }

    }

    fun onNodeClick(meditationName: String, openScreen: (String) -> Unit) {
        openScreen("$MEDITATION_SCREEN/${meditationName}")
    }
}