package com.example.meditategg.screens.map

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.layoutId
import androidx.hilt.navigation.compose.hiltViewModel
import kotlin.math.roundToInt
import com.example.meditategg.R
import com.example.meditategg.model.Meditation

@Composable
fun MapScreen(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MapViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    val configuration = LocalConfiguration.current
    val parentWidth = configuration.screenWidthDp.dp
    val parentHeight = configuration.screenHeightDp.dp
    val minOffsetX = LocalDensity.current.run {(parentWidth/2 - uiState.mapSize/2).toPx()}
    val maxOffsetX = LocalDensity.current.run {(uiState.mapSize/2 - parentWidth/2).toPx()}
    val minOffsetY = LocalDensity.current.run {(parentHeight/2 - uiState.mapSize/2).toPx()}
    val maxOffsetY = LocalDensity.current.run {(uiState.mapSize/2 - parentHeight/2).toPx()}

    Box(
        modifier = modifier
            .requiredSize(uiState.mapSize)
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, drag ->
                    change.consume()
                    offsetX = (offsetX + drag.x).coerceIn(minOffsetX, maxOffsetX)
                    offsetY = (offsetY + drag.y).coerceIn(minOffsetY, maxOffsetY)
                }
            }
    ) {
        Image(
            painter = painterResource(R.drawable.meditation_graph_gradient_labels),
            contentDescription = null,
            modifier = modifier.requiredSize(uiState.mapSize)
        )
        ConstraintLayout(
            viewModel.constraints,
            modifier = modifier.requiredSize(uiState.mapSize)
        ) {
            MeditationNode(Meditation.Posture.name, viewModel::onNodeClick, openScreen)
            MeditationNode(Meditation.RotatingAwareness.name, viewModel::onNodeClick, openScreen)
            MeditationNode(Meditation.Kapalbhati.name, viewModel::onNodeClick, openScreen)
            MeditationNode(Meditation.WhatIsSelf.name, viewModel::onNodeClick, openScreen)
            MeditationNode(Meditation.FollowingBreath.name, viewModel::onNodeClick, openScreen)
            MeditationNode(Meditation.RewindPractice.name, viewModel::onNodeClick, openScreen)
            MeditationNode(Meditation.OmChanting.name, viewModel::onNodeClick, openScreen)
            MeditationNode(Meditation.FacultyOfHearing.name, viewModel::onNodeClick, openScreen)
        }
    }
}

@Composable
fun MeditationNode(
    meditationName: String,
    onNodeClick: (String, (String) -> Unit) -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 35.dp,
) {
    IconButton(
        onClick = { onNodeClick(meditationName, openScreen) },
        modifier = modifier
            .layoutId(meditationName)
            .size(size)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.meditation_node_flat),
            tint = Color.Unspecified,
            contentDescription = null
        )
    }
}