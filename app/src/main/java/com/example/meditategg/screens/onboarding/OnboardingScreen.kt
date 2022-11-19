package com.example.meditategg.screens.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditategg.MAP_SCREEN
import com.example.meditategg.ONBOARDING_SCREEN
import com.example.meditategg.common.composable.GradientButton
import com.example.meditategg.theme.Lora
import com.example.meditategg.theme.Roboto
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(openAndPopup: (String, String) -> Unit) {
    val pages = listOf(
        OnBoardingPage.Variety,
        OnBoardingPage.Focus,
        OnBoardingPage.OpenAwareness,
        OnBoardingPage.Grounding,
        OnBoardingPage.Inquisitive,
        OnBoardingPage.Graph
    )

    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colors.background,
                        MaterialTheme.colors.surface
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = 6,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position -> OnBoardingPageScreen(onBoardingPage = pages[position]) }

        HorizontalPagerIndicator(
            modifier = Modifier.weight(1f),
            pagerState = pagerState
        )

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(visible = pagerState.currentPage == 5) {
                GradientButton(
                    text = "Finish",
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
                    onClick = { openAndPopup(MAP_SCREEN, ONBOARDING_SCREEN) }
                )
            }
        }

    }
}

@Composable
fun OnBoardingPageScreen(onBoardingPage: OnBoardingPage){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(onBoardingPage.icon),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.6f)
        )

        Text(
            text = onBoardingPage.header,
            color = MaterialTheme.colors.primary,
            fontFamily = Lora,
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium,
        )

        Text(
            text = onBoardingPage.description,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 20.dp),
            fontFamily = Roboto,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun ComposablePreview() {
    OnBoardingScreen() { a, b ->

    }
}