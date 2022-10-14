package com.example.meditationapp.meditation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.meditationapp.R
import com.example.meditationapp.Screen
import com.example.meditationapp.ui.theme.Lora

@Composable
fun MeditationScreen(
    meditation: Meditation,
    navController: NavHostController
) {
    val purpleGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xff5B5C95),
            Color(0xff998FB6)
        )
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = purpleGradient),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp)
                .padding(horizontal = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                navController.navigate(Screen.MeditationMap.route)
            }) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(R.drawable.info),
                    contentDescription = "More Info",
                    tint = Color.White
                )
            }
        }
        

        Text(
            text = meditation.name,
            color = Color.White,
            fontSize = 36.sp,
            fontFamily = Lora,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        ) { items(
            items = meditation.instructions,
            itemContent = { item ->
                Spacer(
                    modifier = Modifier
                        .height(18.dp)
                )
                InstructionCard(instruction = item)
            })
        }

    }
}

//@Preview
//@Composable
//fun MeditationScreenDemo() {
//    MeditationScreen(Meditation.ThirdEye)
//}