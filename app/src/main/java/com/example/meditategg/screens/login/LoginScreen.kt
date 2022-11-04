package com.example.meditategg.screens.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import com.example.meditategg.common.composable.*
import com.example.meditategg.theme.Lora
import com.example.meditategg.theme.Roboto
import com.example.meditategg.R.drawable as AppIcon

@Composable
fun LoginScreen(
    openAndPopUp: (String, String) -> Unit,
    popUpScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(listOf(
                    MaterialTheme.colors.background,
                    MaterialTheme.colors.surface
                ))
            )
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp, end = 10.dp)
        ) {
            IconButton(
                onClick = popUpScreen,
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(
                    painter = painterResource(AppIcon.arrow_left),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Back",
                    tint = MaterialTheme.colors.primary
                )
            }

            Text(
                text = "Welcome\nBack!",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(top = 20.dp, start = 30.dp),
                color = MaterialTheme.colors.primary,
                fontSize = 33.sp,
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

            Image(
                painter = painterResource(AppIcon.meditating_girl_illustration),
                contentDescription = "Meditating Girl Illustration",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(200.dp)
                    .padding(top = 10.dp)
            )
        }

        EmailField(
            uiState.email,
            viewModel::onEmailChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp)
        )
        PasswordField(
            uiState.password,
            viewModel::onPasswordChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        GradientButton(
            text = "LOGIN",
            textColor = MaterialTheme.colors.onPrimary,
            fontSize = 20.sp,
            fontFamily = Lora,
            fontWeight = FontWeight.Bold,
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
            width = 300.dp,
            horizontalPad = 50.dp,
            verticalPad = 20.dp,
            elevation = ButtonDefaults.elevation(
                defaultElevation = 4.dp,
                pressedElevation = 0.dp
            ),
            onClick = { viewModel.onSignInClick(openAndPopUp) }
        )

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Or sign in with",
                color = MaterialTheme.colors.primary,
                fontSize = 16.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.width(10.dp))
            Divider(
                color = MaterialTheme.colors.primary,
                thickness = 1.dp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        FacebookButton(
            onFacebookClick = { /*TODO*/ },
            text = "Sign in with Facebook"
        )

        Spacer(modifier = Modifier.height(3.dp))

        GoogleButton(
            onGoogleClick = { /*TODO*/ },
            text = "Sign in with Google"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have account?",
                color = MaterialTheme.colors.primary,
                fontSize = 15.sp,
                fontFamily = Roboto,
                fontWeight = FontWeight.Normal
            )

            TextButton(onClick = { viewModel.onSignUpClick(openAndPopUp) }) {
                Text(
                    text = "Sign Up",
                    color = MaterialTheme.colors.primary,
                    fontSize = 15.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}