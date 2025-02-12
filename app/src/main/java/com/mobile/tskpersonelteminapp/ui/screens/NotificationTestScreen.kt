package com.mobile.tskpersonelteminapp.ui.screens

import android.graphics.drawable.Icon
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import com.mobile.tskpersonelteminapp.viewmodels.NotificationViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun NotificationTestScreen(
    messageText: String,
    onMessageChange: (String) -> Unit,
    onMessageSend: () -> Unit,
    onMessageBroadcast: () -> Unit,
    viewModel: NotificationViewModel
) {

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        val state = viewModel.state
        if (state.isEnteringToken) {
            TokenDialog(
                token = state.remoteToken,
                onTokenChange = viewModel::onRemoteTokenChange,
                onSubmit = viewModel::onSubmitToken
            )
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = messageText,
                    onValueChange = onMessageChange,
                    placeholder = {
                        Text("Enter a message")
                    }, modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp
                        ),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = onMessageSend) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = "send")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    IconButton(onClick = onMessageBroadcast) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "broadcast")
                    }
                }
            }
        }
    }


}//

@Composable
fun TokenDialog(
    token: String,
    onTokenChange: (String) -> Unit,
    onSubmit: () -> Unit
) {
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = token,
                onValueChange = onTokenChange,
                modifier = Modifier.fillMaxWidth(), placeholder = {
                    Text(text = "Remote User token")
                },
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(onClick = {
                    scope.launch {
                        val localToken = Firebase.messaging.token.await()
                        clipboardManager.setText(AnnotatedString(localToken))
                        Toast.makeText(context, "Copied local token!", Toast.LENGTH_LONG).show()
                    }
                }) {
                    Text(text = "copy token")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = onSubmit) {
                    Text("submit")
                }
            }
        }
    }


}