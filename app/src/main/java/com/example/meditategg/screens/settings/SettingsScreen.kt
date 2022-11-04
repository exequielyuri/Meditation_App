package com.example.meditategg.screens.settings

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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

        Image(
            painter = painterResource(AppIcon.sample_profile),
            contentDescription = "Profile picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(
                    3.dp,
                    MaterialTheme.colors.primary,
                    CircleShape
                )
                .shadow(
                    elevation = 4.dp,
                    clip = true
                )
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Sasha Braus",
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

        if (uiState.isAnonymousAccount) {
            RegularCardEditor(AppText.sign_in, AppIcon.ic_sign_in, "", Modifier.padding(16.dp, 0.dp, 16.dp, 8.dp)) {
                viewModel.onLoginClick(openScreen)
            }

            RegularCardEditor(AppText.create_account, AppIcon.ic_create_account, "", Modifier.padding(16.dp, 0.dp, 16.dp, 8.dp)) {
                viewModel.onSignUpClick(openScreen)
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

    RegularCardEditor(AppText.sign_out, AppIcon.ic_exit, "", Modifier.padding(16.dp, 0.dp, 16.dp, 8.dp)) {
        showWarningDialog = true
    }

    if(showWarningDialog) {
        AlertDialog(
            title = { Text(stringResource(AppText.sign_out_title)) },
            text = { Text(stringResource(AppText.sign_out_description)) },
            dismissButton = { DialogCancelButton(AppText.cancel) { showWarningDialog = false } },
            confirmButton = {
                DialogConfirmButton(AppText.sign_out) {
                    signOut()
                    showWarningDialog = false
                }
            },
            onDismissRequest = { showWarningDialog = false }
        )
    }
}

@ExperimentalMaterialApi
@Composable
private fun DeleteMyAccountCard(deleteMyAccount: () -> Unit) {
    var showWarningDialog by remember { mutableStateOf(false) }

    DangerousCardEditor(AppText.delete_my_account, AppIcon.ic_delete_my_account, "", Modifier.padding(16.dp, 0.dp, 16.dp, 8.dp)) {
        showWarningDialog = true
    }

    if(showWarningDialog) {
        AlertDialog(
            title = { Text(stringResource(AppText.delete_account_title)) },
            text = { Text(stringResource(AppText.delete_account_description)) },
            dismissButton = { DialogCancelButton(AppText.cancel) { showWarningDialog = false } },
            confirmButton = {
                DialogConfirmButton(AppText.delete_my_account) {
                    deleteMyAccount()
                    showWarningDialog = false
                }
            },
            onDismissRequest = { showWarningDialog = false }
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun DangerousCardEditor(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    content: String,
    modifier: Modifier,
    onEditClick: () -> Unit
) {
    CardEditor(title, icon, content, onEditClick, MaterialTheme.colors.primary, modifier)
}

@ExperimentalMaterialApi
@Composable
fun RegularCardEditor(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    content: String,
    modifier: Modifier,
    onEditClick: () -> Unit
) {
    CardEditor(title, icon, content, onEditClick, MaterialTheme.colors.onSurface, modifier)
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

@Composable
fun DialogConfirmButton(@StringRes text: Int, action: () -> Unit) {
    Button(
        onClick = action,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        )
    ) {
        Text(text = stringResource(text))
    }
}

@Composable
fun DialogCancelButton(@StringRes text: Int, action: () -> Unit) {
    Button(
        onClick = action,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.onPrimary,
            contentColor = MaterialTheme.colors.primary
        )
    ) {
        Text(text = stringResource(text))
    }
}