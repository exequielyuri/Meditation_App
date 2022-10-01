package com.example.meditationapp.meditation

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import kotlin.math.roundToInt
import com.example.meditationapp.R

@Composable
fun MeditationGraph() {
    val constraints = ConstraintSet {
        val posture = createRefFor(id = "posture")
        val ice = createRefFor(id = "ice")
        val trataka = createRefFor(id = "trataka")
        val bee_breath = createRefFor(id = "bee_breath")
        val mindfulness = createRefFor(id = "mindfulness")
        val custom1 = createRefFor(id = "custom1")

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

        constrain(custom1) {
            top.linkTo(posture.bottom, 190.dp)
            start.linkTo(parent.start, 160.dp)
            end.linkTo(parent.end)
        }

    }

    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    val graphSize = 1000.dp

    val configuration = LocalConfiguration.current
    val parentWidth = configuration.screenWidthDp.dp
    val parentHeight = configuration.screenHeightDp.dp
    val minOffsetX = LocalDensity.current.run {(parentWidth/2 - graphSize/2).toPx()}
    val maxOffsetX = LocalDensity.current.run {(graphSize/2 - parentWidth/2).toPx()}
    val minOffsetY = LocalDensity.current.run {(parentHeight/2 - graphSize/2).toPx()}
    val maxOffsetY = LocalDensity.current.run {(graphSize/2 - parentHeight/2).toPx()}

    Box(
        modifier = Modifier
            .requiredSize(graphSize)
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consumeAllChanges()
                    offsetX = (offsetX + dragAmount.x).coerceIn(
                        minOffsetX,
                        maxOffsetX
                    )
                    offsetY = (offsetY + dragAmount.y).coerceIn(
                        minOffsetY,
                        maxOffsetY
                    )
                }
            }
    ) {
        Image(
            painter = painterResource(R.drawable.graph_background),
            contentDescription = "",
            modifier = Modifier.requiredSize(graphSize))

        ConstraintLayout(
        constraints,
        modifier = Modifier
            .requiredSize(graphSize)

        ) {

            MeditationNode(
                name = "posture",
                size = 0.dp
            )
            MeditationNode(name = "ice")
            MeditationNode(name = "trataka")
            MeditationNode(name = "bee_breath")
            MeditationNode(name = "mindfulness")
            MeditationNode(name = "custom1")
        }
    }
}

@Composable
fun MeditationNode(
    modifier: Modifier = Modifier,
    name: String,
    size: Dp = 30.dp
) {
    IconButton(
        onClick = { /*TODO*/ },
        modifier = modifier
            .layoutId(name)
            .size(size)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.meditation_ball),
            tint = Color.Unspecified,
            contentDescription = null
        )
    }
}

