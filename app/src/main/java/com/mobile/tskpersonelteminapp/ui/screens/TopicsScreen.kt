package com.mobile.tskpersonelteminapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.DestinationScreen
import com.mobile.tskpersonelteminapp.data.models.User
import com.mobile.tskpersonelteminapp.ui.components.ComminityCustomCard
import com.mobile.tskpersonelteminapp.ui.components.ComminityHeader
import com.mobile.tskpersonelteminapp.ui.components.TopicCustomCard
import com.mobile.tskpersonelteminapp.ui.theme.adContainerColor
import com.mobile.tskpersonelteminapp.ui.theme.background
import com.mobile.tskpersonelteminapp.ui.theme.buttonColor
import com.mobile.tskpersonelteminapp.ui.theme.line
import com.mobile.tskpersonelteminapp.ui.theme.offWhite
import com.mobile.tskpersonelteminapp.ui.theme.outlinedColor
import com.mobile.tskpersonelteminapp.utils.ObserveErrorMessage
import com.mobile.tskpersonelteminapp.utils.navigateTo
import com.mobile.tskpersonelteminapp.viewmodels.AuthenticationViewModel
import com.mobile.tskpersonelteminapp.viewmodels.ComminityViewModel

@Composable
fun TopicsScreen(
    navController: NavController,
    comminityVm: ComminityViewModel,
    authViewModel: AuthenticationViewModel,
    themeId: String
) {
    // val signIn = viewModel.signIn.value

    val errorMessageCv by comminityVm.errorMessage
    val errorMessageAuth by authViewModel.errorMessage

    ObserveErrorMessage(errorMessageAuth)
    ObserveErrorMessage(errorMessageCv)

    LaunchedEffect(Unit) {
        comminityVm.getTopics(themeId)
    }

    val topics = comminityVm.topics.value

    val userData = authViewModel.userData.value
    val user = User(name = userData?.name, userId = userData?.userId, email = userData?.email)

    val showDialogAccount = remember {
        mutableStateOf(false)
    }

    val onDismissAAD: () -> Unit = { showDialogAccount.value = false }//AccountAlertDialog
    val onViewAAD: () -> Unit = { showDialogAccount.value = true }

    val showDialogTopic = remember {
        mutableStateOf(false)
    }

    val onDismissTopicAdd: () -> Unit = { showDialogTopic.value = false }
    val onViewTopicAdd: () -> Unit = { showDialogTopic.value = true }

    ShowADAccount(
        onDismiss = onDismissAAD,
        viewModel = authViewModel,
        showDialog = showDialogAccount.value,
        navController = navController
    )
    ShowADTopic(
        showDialog = showDialogTopic.value,
        onDismiss = onDismissTopicAdd,
        authVm = authViewModel,
        navController = navController,
        commnityVm = comminityVm,
        themeId = themeId,
        user = user
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background)
    ) {
        ComminityHeader("Konular", onBackClicked = {
            navController.popBackStack()
        }, onAccountClicked = {
            onViewAAD.invoke()
        }, onAddClicked = {
            onViewTopicAdd.invoke()
        })
        ComminityCustomCard(
            content = comminityVm.themes.value.find { it.themeId == themeId }?.theme.toString(),
            modifier = Modifier
        )
        Divider(color = Color.Gray, modifier = Modifier.padding(start = 4.dp, end = 4.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(topics) {
                TopicCustomCard(
                    content = it.topic!!, modifier = Modifier.clickable {
                        navigateTo(
                            navController,
                            DestinationScreen.Comments.createRoute(
                                themeId = themeId,
                                topicId = it.topicId!!
                            )
                        )
                    }, date = it.date!!, userName = it.user?.name!!,
                    pinned = it.pinned ?: false
                )
            }
        }
    }
}


@Composable
fun ShowADAccount(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    viewModel: AuthenticationViewModel,
    navController: NavController
) {

    val signIn = viewModel.signIn.value
    if (showDialog) {
        if (signIn) {
            AlertDialog(containerColor = adContainerColor,
                onDismissRequest = {
                    onDismiss.invoke()
                },
                confirmButton = {
                    Button(onClick = {
                        navigateTo(
                            navController = navController,
                            DestinationScreen.Profile.route
                        )
                    }, colors = buttonColor()) {
                        Text(text = "Profilime git")
                    }
                },
                title = { Text(text = "Profilim", color = line) },
                text = { Text(text = "Profili görüntülemek için tıklayın.", color = line) })
        } else {
            AlertDialog(
                containerColor =adContainerColor,
                onDismissRequest = {
                    onDismiss.invoke()
                },
                confirmButton = {
                    Button(onClick = {
                        navigateTo(
                            navController = navController,
                            DestinationScreen.SignUp.route
                        )
                    }, colors = buttonColor()) {
                        Text(text = "Giriş yap")
                    }
                },
                title = { Text(text = "Giriş Yap", color = line) },
                text = { Text(text = "Henüz giriş yapmadınız! Giriş yapmak için tıklayın.", color = line) })
        }
    }
}

@Composable
fun ShowADTopic(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    authVm: AuthenticationViewModel,
    navController: NavController,
    commnityVm: ComminityViewModel,
    themeId: String,
    user: User
) {
    val signIn = authVm.signIn.value

    val topicState = remember {
        mutableStateOf(TextFieldValue())
    }
    if (showDialog) {
        topicState.value=TextFieldValue()
        if (signIn) {
            AlertDialog(containerColor = adContainerColor,
                onDismissRequest = {
                    onDismiss.invoke()
                },
                confirmButton = {
                    Button(onClick = {
                        //soru sor
                        commnityVm.addTopics(topic = topicState.value.text, user, themeId = themeId)
                        onDismiss.invoke()
                    }, colors = buttonColor()) {
                        Text(text = "Soru sor")
                    }
                },
                title = { Text(text = "Soru", color = line) },
                text = {
                    OutlinedTextField(value = topicState.value, maxLines = 3,onValueChange = {
                        topicState.value = it
                    }, colors = outlinedColor())
                })
        } else {
            AlertDialog(containerColor = adContainerColor,
                onDismissRequest = {
                    onDismiss.invoke()
                },
                confirmButton = {
                    Button(onClick = {
                        navigateTo(
                            navController = navController,
                            DestinationScreen.SignUp.route
                        )
                    },colors = buttonColor()) {
                        Text(text = "Giriş yap")
                    }
                },
                title = { Text(text = "Giriş Yap", color = line) },
                text = { Text(text = "Giriş yapmadan soru soramazsınız. Giriş yapmak için tıklayın.", color = line) })
        }
    }
}