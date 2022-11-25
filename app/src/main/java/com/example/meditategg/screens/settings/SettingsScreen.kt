package com.example.meditategg.screens.settings

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.meditategg.common.composable.SettingsCard
import com.example.meditategg.common.composable.StatisticButton
import com.example.meditategg.theme.Lora
import com.example.meditategg.R.drawable as AppIcon
import com.example.meditategg.R.string as AppText

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsScreen(
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    LaunchedEffect(Unit) { viewModel.initialize() }

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
            )
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.padding(18.dp))
        if (uiState.isAnonymousAccount) {
            Image(
                painter = painterResource(AppIcon.reindeer),
                contentDescription = "Profile picture",
                contentScale = ContentScale.Fit,
                modifier = Modifier.height(150.dp)
            )
        } else {
            Image(
                painter = painterResource(AppIcon.anonymous_profile_pink),
                contentDescription = "Profile picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(150.dp)
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = uiState.name,
            color = MaterialTheme.colors.primary,
            fontSize = 20.sp,
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

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Statistics",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp),
            color = MaterialTheme.colors.primary,
            fontSize = 20.sp,
            fontFamily = Lora,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(15.dp))

        LazyRow(verticalAlignment = Alignment.CenterVertically) {
            item {
                Spacer(modifier = Modifier.width(15.dp))
                StatisticButton(
                    icon = AppIcon.ic_calendar_check,
                    header = "24 day",
                    label = "streak",
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            item {
                StatisticButton(
                    icon = AppIcon.ic_hourglass,
                    header = "521 hours",
                    label = "of meditation"
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            item {
                StatisticButton(
                    header = "Metta\nMeditation",
                    label = "most replayed"
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            item {
                StatisticButton(
                    icon = AppIcon.ic_calendar_check,
                    header = "24 day",
                    label = "streak",
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            item {
                StatisticButton(
                    icon = AppIcon.ic_hourglass,
                    header = "521 hours",
                    label = "of meditation"
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            item {
                StatisticButton(
                    header = "Metta\nMeditation",
                    label = "most replayed"
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            item {
                Spacer(modifier = Modifier.width(15.dp))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Preferences",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp),
            color = MaterialTheme.colors.primary,
            fontSize = 20.sp,
            fontFamily = Lora,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(10.dp))

        SettingsCard(
            text = "Favorite Meditations",
            leadingIcon = AppIcon.ic_heart,
            onClick = { /*TODO*/ }
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(AppIcon.arrow_right),
                    modifier = Modifier.size(20.dp),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
        }

        SettingsCard(
            text = "More Information",
            leadingIcon = AppIcon.ic_help,
            onClick = { /*TODO*/ }
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(AppIcon.arrow_right),
                    modifier = Modifier.size(20.dp),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
        }

        SettingsCard(
            text = "Reminder",
            leadingIcon = AppIcon.ic_reminder,
            onClick = { /*TODO*/ }
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(AppIcon.arrow_right),
                    modifier = Modifier.size(20.dp),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
        }

        SettingsCard(
            text = "Daily quotes",
            leadingIcon = AppIcon.ic_quote,
            onClick = { /*TODO*/ }
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(AppIcon.arrow_right),
                    modifier = Modifier.size(20.dp),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
        }

        SettingsCard(
            text = "Theme",
            leadingIcon = AppIcon.ic_theme,
            onClick = { /*TODO*/ }
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(AppIcon.arrow_right),
                    modifier = Modifier.size(20.dp),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Account",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp),
            color = MaterialTheme.colors.primary,
            fontSize = 20.sp,
            fontFamily = Lora,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (uiState.isAnonymousAccount) {

            SettingsCard(
                text = stringResource(AppText.sign_in),
                leadingIcon = AppIcon.ic_sign_in,
                onClick = { viewModel.onLoginClick(openScreen) }
            ) {
                IconButton(onClick = { viewModel.onLoginClick(openScreen) }) {
                    Icon(
                        painter = painterResource(AppIcon.arrow_right),
                        modifier = Modifier.size(20.dp),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary
                    )
                }
            }

            SettingsCard(
                text = stringResource(AppText.create_account),
                leadingIcon = AppIcon.ic_create_account,
                onClick = { viewModel.onSignUpClick(openScreen) }
            ) {
                IconButton(onClick = { viewModel.onSignUpClick(openScreen) }) {
                    Icon(
                        painter = painterResource(AppIcon.arrow_right),
                        modifier = Modifier.size(20.dp),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary
                    )
                }
            }

        } else {
            SignOutCard { viewModel.onSignOutClick(restartApp) }
            DeleteMyAccountCard { /*TODO: onDeleteMyAccount*/ }
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@ExperimentalMaterialApi
@Composable
private fun SignOutCard(signOut: () -> Unit) {
    var showWarningDialog by remember { mutableStateOf(false) }

    SettingsCard(
        text = stringResource(AppText.sign_out),
        leadingIcon = AppIcon.ic_exit,
        onClick = { showWarningDialog = true }
    ) {
        IconButton(onClick = { showWarningDialog = true }) {
            Icon(
                painter = painterResource(AppIcon.arrow_right),
                modifier = Modifier.size(20.dp),
                contentDescription = null,
                tint = MaterialTheme.colors.primary
            )
        }
    }

    if(showWarningDialog) {
        AlertDialog(
            title = {
                Text(
                    text = stringResource(AppText.sign_out_title),
                    color = MaterialTheme.colors.primary
                )
            },
            text = {
                Text(
                    text = stringResource(AppText.sign_out_description),
                    color = MaterialTheme.colors.primaryVariant
                )
            },
            dismissButton = {
                Button(
                    onClick = { showWarningDialog = false },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 5.dp,
                        pressedElevation = 0.dp
                    )
                ) {
                    Text(
                        text = stringResource(AppText.cancel),
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.h4.copy(
                            shadow = Shadow(
                                color = MaterialTheme.colors.primary,
                                offset = Offset(0f, 1f),
                                blurRadius = 1f
                            )
                        )
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        signOut()
                        showWarningDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 5.dp,
                        pressedElevation = 0.dp
                    )
                ) {
                    Text(
                        text = stringResource(AppText.sign_out),
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.h4.copy(
                            shadow = Shadow(
                                color = MaterialTheme.colors.surface,
                                offset = Offset(0f, 1f),
                                blurRadius = 1f
                            )
                        )
                    )
                }
            },
            onDismissRequest = { showWarningDialog = false },
            backgroundColor = MaterialTheme.colors.background
        )
    }
}

@ExperimentalMaterialApi
@Composable
private fun DeleteMyAccountCard(deleteMyAccount: () -> Unit) {
    var showWarningDialog by remember { mutableStateOf(false) }

    SettingsCard(
        text = stringResource(AppText.delete_my_account),
        leadingIcon = AppIcon.ic_delete_my_account,
        onClick = { showWarningDialog = true }
    ) {
        IconButton(onClick = { showWarningDialog = true }) {
            Icon(
                painter = painterResource(AppIcon.arrow_right),
                modifier = Modifier.size(20.dp),
                contentDescription = null,
                tint = MaterialTheme.colors.primary
            )
        }
    }

    if(showWarningDialog) {
        AlertDialog(
            title = {
                Text(
                    text = stringResource(AppText.delete_account_title),
                    color = MaterialTheme.colors.primary
                )
            },
            text = {
                Text(
                    text = stringResource(AppText.delete_account_description),
                    color = MaterialTheme.colors.primary
                )
            },
            dismissButton = {
                Button(
                    onClick = { showWarningDialog = false },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 5.dp,
                        pressedElevation = 0.dp
                    )
                ) {
                    Text(
                        text = stringResource(AppText.cancel),
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.h4.copy(
                            shadow = Shadow(
                                color = MaterialTheme.colors.primary,
                                offset = Offset(0f, 1f),
                                blurRadius = 1f
                            )
                        )
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        deleteMyAccount()
                        showWarningDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 5.dp,
                        pressedElevation = 0.dp
                    )
                ) {
                    Text(
                        text = stringResource(AppText.delete_my_account),
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.h4.copy(
                            shadow = Shadow(
                                color = MaterialTheme.colors.surface,
                                offset = Offset(0f, 1f),
                                blurRadius = 1f
                            )
                        )
                    )
                }
            },
            onDismissRequest = { showWarningDialog = false },
            backgroundColor = MaterialTheme.colors.background
        )
    }
}

@ExperimentalMaterialApi
@Composable
private fun CardEditor(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    content: String,
    onEditClick: () -> Unit,
    highlightColor: Color,
    modifier: Modifier
) {
    Card(backgroundColor = MaterialTheme.colors.onPrimary, modifier = modifier, onClick = onEditClick) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(stringResource(title), color = highlightColor)
            }

            if (content.isNotBlank()) {
                Text(text = content, modifier = Modifier.padding(16.dp, 0.dp))
            }

            Icon(
                painter = painterResource(icon),
                contentDescription = "Icon",
                tint = highlightColor
            )
        }
    }
}