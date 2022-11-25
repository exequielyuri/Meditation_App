package com.example.meditategg.common.composable

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.meditategg.R.drawable as AppIcon
import com.example.meditategg.model.JournalEntry
import com.example.meditategg.theme.Lora

@Composable
fun MeditationCompletionDialog(
    modifier: Modifier = Modifier,
    userReflection: String,
    durationSec: Int,
    onReflectionChange: (String) -> Unit,
    onConfirmClick: (Context) -> Unit,
    onRetry: () -> Unit,
) {
    Dialog(onDismissRequest = onRetry) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colors.background, RoundedCornerShape(4.dp)),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = if (durationSec>60 && durationSec/60>1) {
                    "Meditated for ${durationSec/60} minutes"
                } else if (durationSec>60) {
                    "Meditated for 1 minute"
                } else if (durationSec%60>1) {
                    "Meditated for ${durationSec%60} seconds"
                } else "Meditated for 1 second",
                modifier = modifier.padding(start = 20.dp, end = 20.dp, top = 15.dp, bottom = 4.dp),
                color = MaterialTheme.colors.primary,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Lora
            )

            Box(modifier = modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = userReflection,
                    onValueChange = { onReflectionChange(it) },
                    modifier = modifier.padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.primaryVariant,
                        fontSize = 15.sp,
                        fontFamily = Lora,
                    ),
                    label = {
                        Text(
                            text = "How was your experience?",
                            color = MaterialTheme.colors.primaryVariant,
                            fontSize = 15.sp,
                            fontFamily = Lora
                        )
                    },
                    maxLines = 3
                )
            }


            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onRetry,
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 5.dp,
                        pressedElevation = 0.dp
                    )
                ) {
                    Text(
                        text = "Retry",
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

                Spacer(modifier = modifier.width(8.dp))

                val context = LocalContext.current
                Button(
                    onClick = { onConfirmClick(context) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 5.dp,
                        pressedElevation = 0.dp
                    )
                ) {
                    Text(
                        text = "Confirm",
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
            }
        }
    }
}

@Composable
fun JournalDialog(
    modifier: Modifier = Modifier,
    entry: JournalEntry,
    showMoreContents: MutableState<Boolean>,
    onBookmarkClick: (JournalEntry) -> Unit
) {
    Dialog(onDismissRequest = { showMoreContents.value = !showMoreContents.value }) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .height(200.dp)
                .background(MaterialTheme.colors.background, RoundedCornerShape(4.dp))
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = entry.meditationName,
                    color = MaterialTheme.colors.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Lora
                )

                IconButton(onClick = { onBookmarkClick(entry) }) {
                    Icon(
                        painter = if (entry.bookmark) painterResource(AppIcon.ic_bookmark) else painterResource(AppIcon.ic_bookmark_border),
                        contentDescription = "Bookmark",
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colors.primary
                    )
                }
            }

            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 17.dp, bottom = 20.dp)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        Text(
                            text = entry.content,
                            color = MaterialTheme.colors.primaryVariant,
                            fontSize = 16.sp,
                            fontFamily = Lora
                        )
                    }
                }
            }
        }
    }
}