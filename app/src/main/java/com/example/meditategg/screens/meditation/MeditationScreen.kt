package com.example.meditategg.screens.meditation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditategg.R.drawable as AppIcon
import com.example.meditategg.common.composable.ExpandableCard
import com.example.meditategg.common.composable.GradientButton
import com.example.meditategg.common.composable.MeditationCompletionDialog
import com.example.meditategg.model.Meditation
import com.example.meditategg.theme.*
import kotlinx.coroutines.delay
import java.text.DecimalFormat
import kotlin.time.Duration.Companion.seconds

@Composable
fun MeditationScreen(
    modifier: Modifier = Modifier,
    meditation: Meditation,
    popUpScreen: () -> Unit,
    viewModel: MeditationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
    viewModel.initNewEntry(meditation.name)

    LaunchedEffect(key1 = uiState.durationSec, key2 = uiState.meditating) {
        if(uiState.meditating) {
            delay(1.seconds)
            viewModel.incrementTimer()
        }
    }

    if (uiState.openDialog) {
        MeditationCompletionDialog(
            userReflection = uiState.userReflection,
            durationSec = viewModel.entry.value.durationSec,
            onReflectionChange = viewModel::onReflectionChange,
            onConfirmClick = viewModel::onConfirmClick,
            onRetry = viewModel::onRetry
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colors.background,
                        MaterialTheme.colors.surface
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 25.dp)
                .padding(horizontal = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = popUpScreen) {
                Icon(
                    painter = painterResource(AppIcon.arrow_left),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Back",
                    tint = MaterialTheme.colors.primary
                )
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(AppIcon.ic_info),
                    modifier = Modifier.size(28.dp),
                    contentDescription = "More Info",
                    tint = MaterialTheme.colors.primary
                )
            }
        }

        Text(
            text = meditation.name,
            color = MaterialTheme.colors.primary,
            fontSize = 36.sp,
            fontFamily = Lora,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.h4.copy(
                shadow = Shadow(
                    color = MaterialTheme.colors.secondaryVariant,
                    offset = Offset(0f, 3f),
                    blurRadius = 5f
                )
            )
        )

        Spacer(modifier = modifier.height(15.dp))

        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(5f)
                    .padding(horizontal = 30.dp)
            ) {
                item { Spacer(modifier = modifier.height(20.dp)) }

                items(items = meditation.instructions) { item ->
                    Spacer(modifier = modifier.height(2.dp))
                    ExpandableCard(instruction = item)
                }

               item { Spacer(modifier = modifier.height(20.dp)) }
            }

            Box(
                modifier = modifier.weight(1f),
                contentAlignment = Alignment.Center
            ){
                val df = DecimalFormat("00")
                GradientButton(
                    text = if (uiState.meditating) {
                            "${df.format(uiState.durationSec/60)}:${df.format(uiState.durationSec%60)}"
                        } else "Practice",
                    textColor = MaterialTheme.colors.onPrimary,
                    fontSize = 18.sp,
                    fontFamily = Lora,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.h4.copy(
                        shadow = Shadow(
                            color = MaterialTheme.colors.primary,
                            offset = Offset(0f, 1f),
                            blurRadius = 1f
                        )
                    ),
                    gradient = Brush.horizontalGradient(
                        listOf(
                            MaterialTheme.colors.primary,
                            MaterialTheme.colors.primaryVariant
                        )
                    ),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 0.dp
                    ),
                    horizontalPad = 30.dp,
                    verticalPad = 10.dp,
                    onClick = {
                        if (uiState.meditating) { viewModel.onMeditationDone() }
                        viewModel.toggleTimer()
                    }
                )
            }
        }
    }
}