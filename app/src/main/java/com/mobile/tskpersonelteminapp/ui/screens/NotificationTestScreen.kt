package com.mobile.tskpersonelteminapp.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.tskpersonelteminapp.viewmodels.NotificationViewModel



@Composable
fun NotificationScreen(viewModel: NotificationViewModel) {
    var message by remember { mutableStateOf("") }
    var isBroadcast by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bildirim Gönder",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Mesajınızı Girin") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = false,
            maxLines = 3
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isBroadcast,
                onCheckedChange = { isBroadcast = it }
            )
            Text(text = "Tüm Kullanıcılara Gönder")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.sendMessage(message, isBroadcast)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Gönder")
        }
    }
}



/*
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


}*/