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
        val om_chanting = createRefFor(Meditation.OmChanting.name)
        val faculty_of_hearing = createRefFor(Meditation.FacultyOfHearing.name)

        constrain(posture) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(rotating_awareness) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start, 300.dp)
            end.linkTo(parent.end)
        }

        constrain(kapalbhati) {
            top.linkTo(parent.top, 200.dp)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(what_is_self) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom, 430.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end, 180.dp)
        }

        constrain(following_breath) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom, 280.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(rewind_practice) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom, 270.dp)
            start.linkTo(parent.start, 200.dp)
            end.linkTo(parent.end)
        }

        constrain(om_chanting) {
            top.linkTo(parent.top, 400.dp)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(faculty_of_hearing) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom, 250.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end, 280.dp)
        }

    }

    fun onNodeClick(meditationName: String, openScreen: (String) -> Unit) {
        openScreen("$MEDITATION_SCREEN/${meditationName}")
    }
}