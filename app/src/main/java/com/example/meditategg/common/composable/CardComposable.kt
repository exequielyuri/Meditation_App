package com.example.meditategg.common.composable

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import com.example.meditategg.R
import com.example.meditategg.common.ext.toMonthAbrv
import com.example.meditategg.model.Instruction
import com.example.meditategg.model.JournalEntry
import com.example.meditategg.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableCard(
    modifier: Modifier = Modifier,
    instruction: Instruction
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
                    brush = Brush.horizontalGradient(
                        listOf(
                            MaterialTheme.colors.primary,
                            MaterialTheme.colors.primaryVariant
                        )
                    )
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
                        tint = MaterialTheme.colors.onPrimary
                    )
                }

                Text(
                    text = instruction.header,
                    color = MaterialTheme.colors.onPrimary,
                    fontSize = 16.sp,
                    fontFamily = Lora,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h4.copy(
                        shadow = Shadow(
                            color = MaterialTheme.colors.surface,
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
                    color = MaterialTheme.colors.onPrimary,
                    fontSize = 16.sp,
                    fontFamily = Roboto
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PageCard(
    entry: JournalEntry,
    onBookmarkClick: (JournalEntry) -> Unit
) {
    val showMoreContents = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(horizontal = 15.dp)
            .padding(top = 18.dp),
        shape = MaterialTheme.shapes.small.copy(CornerSize(8.dp)),
        backgroundColor = MaterialTheme.colors.background,
        onClick = { showMoreContents.value = true },
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .width(70.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = entry.month.toMonthAbrv(),
                    fontSize = 18.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.secondary
                )
                Text(
                    text = "${entry.day}",
                    fontSize = 30.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                    color = MaterialTheme.colors.secondary
                )
            }

            Divider(
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 15.dp, end = 20.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = entry.meditationName,
                    fontSize = 20.sp,
                    fontFamily = Lora,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = entry.content,
                    fontSize = 16.sp,
                    fontFamily = Lora,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.primaryVariant,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
        }
    }

    if (showMoreContents.value) {
        JournalDialog(
            entry = entry,
            showMoreContents = showMoreContents,
            onBookmarkClick = onBookmarkClick
        )
    }
}

@Composable
fun SettingsCard(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colors.primary,
    @DrawableRes leadingIcon: Int,
    onClick: () -> Unit,
    trailingButton: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(30.dp))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(leadingIcon),
                    modifier = Modifier.size(24.dp),
                    contentDescription = null,
                    tint = color
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = text,
                    color = color,
                    fontSize = 16.sp,
                    fontFamily = Roboto,
                )
            }
            trailingButton()
        }
        Spacer(modifier = Modifier.width(30.dp))
    }
}