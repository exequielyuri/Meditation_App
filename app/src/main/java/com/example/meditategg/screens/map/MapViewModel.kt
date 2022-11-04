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
        val posture = createRefFor(id = Meditation.Posture.name)
        val ice = createRefFor(id = Meditation.Ice.name)
        val trataka = createRefFor(id = Meditation.Trataka.name)
        val bee_breath = createRefFor(id = Meditation.BeesBreath.name)
        val mindfulness = createRefFor(id = Meditation.Mindfulness.name)
        val thirdeye = createRefFor(id = Meditation.ThirdEye.name)

        constrain(posture) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(ice) {
            bottom.linkTo(posture.top, 200.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(trataka) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(posture.end, 200.dp)
        }

        constrain(bee_breath) {
            bottom.linkTo(trataka.top, 50.dp)
            start.linkTo(parent.start, 200.dp)
            end.linkTo(parent.end)
        }

        constrain(mindfulness) {
            top.linkTo(posture.bottom, 70.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end, 200.dp)
        }

        constrain(thirdeye) {
            top.linkTo(posture.bottom, 190.dp)
            start.linkTo(parent.start, 160.dp)
            end.linkTo(parent.end)
        }

    }

    fun onNodeClick(meditationName: String, openScreen: (String) -> Unit) {
        openScreen("$MEDITATION_SCREEN/${meditationName}")
    }
}