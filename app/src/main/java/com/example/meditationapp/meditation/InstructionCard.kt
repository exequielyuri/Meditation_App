package com.example.meditationapp.meditation

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.style.TextOverflow
import com.example.meditationapp.R
import com.example.meditationapp.journal.BottomShadow
import com.example.meditationapp.ui.theme.*


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InstructionCard(
    modifier: Modifier = Modifier,
    instruction: Instruction,
    contentColor: Color = Color.White,
    backgroundGradient: Brush = Brush.horizontalGradient(
        listOf(
            Purple50,
            Purple60
        )
    )
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        elevation = 3.dp,
        shape = MaterialTheme.shapes.medium.copy(CornerSize(8.dp)),
        onClick = { expandedState = !expandedState }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = backgroundGradient
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    modifier = Modifier
                        .rotate(rotationState),
                    onClick = { expandedState = !expandedState }
                ) {
                    Icon(
                        modifier = Modifier
                            .shadow(
                                elevation = 4.dp,
                                shape = CircleShape
                            ),
                        painter = painterResource(R.drawable.drop_down),
                        contentDescription = "Expand instruction",
                        tint = contentColor
                    )
                }

                Text(
                    text = instruction.header,
                    color = contentColor,
                    fontSize = 16.sp,
                    fontFamily = Lora,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h4.copy(
                        shadow = Shadow(
                            color = Purple20,
                            offset = Offset(0f, 1f),
                            blurRadius = 1f
                        )
                    )
                )
            }
            if (expandedState) {
                BottomShadow()

                Text(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 5.dp, bottom = 15.dp),
                    text = instruction.body,
                    color = contentColor,
                    fontSize = 16.sp,
                    fontFamily = Roboto
                )
            }
        }
    }
}

@Preview
@Composable
fun InstructionCardPreview() {
    InstructionCard(
        instruction = Instruction(
            "Place middle finger to forehead",
            "Take your middle finger and hover it a centimeter from the middle of your eyebrows (don't touch)."
        )
    )
}