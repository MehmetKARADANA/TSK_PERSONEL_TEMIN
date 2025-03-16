package com.mobile.tskpersonelteminapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import com.mobile.tskpersonelteminapp.ui.components.ComminityHeader
import com.mobile.tskpersonelteminapp.ui.components.TopicCustomCard
import com.mobile.tskpersonelteminapp.ui.theme.adContainerColor
import com.mobile.tskpersonelteminapp.ui.theme.background
import com.mobile.tskpersonelteminapp.ui.theme.buttonColor
import com.mobile.tskpersonelteminapp.ui.theme.line
import com.mobile.tskpersonelteminapp.ui.theme.outlinedColor
import com.mobile.tskpersonelteminapp.utils.ObserveErrorMessage
import com.mobile.tskpersonelteminapp.utils.navigateTo
import com.mobile.tskpersonelteminapp.viewmodels.AuthenticationViewModel
import com.mobile.tskpersonelteminapp.viewmodels.ComminityViewModel

@Composable
fun CommentScreen(
    navController: NavController,
    comminityVm: ComminityViewModel,
    authenticationViewModel: AuthenticationViewModel,
    themeId: String,
    topicId: String
) {
    val errorMessageCv by comminityVm.errorMessage
    val errorMessageAuth by authenticationViewModel.errorMessage

    ObserveErrorMessage(errorMessageAuth)
    ObserveErrorMessage(errorMessageCv)

    LaunchedEffect(Unit) {
        comminityVm.getComments(themeId = themeId, topicId = topicId)
    }

    val comments = comminityVm.comments.value

    val userData = authenticationViewModel.userData.value
    val user = User(name = userData?.name, userId = userData?.userId, email = userData?.email)


    val showDialogAccount = remember {
        mutableStateOf(false)
    }

    val onDismissAAD: () -> Unit = { showDialogAccount.value = false }//AccountAlertDialog
    val onViewAAD: () -> Unit = { showDialogAccount.value = true }

    val showDialogComment = remember {
        mutableStateOf(false)
    }

    val onDismissCommentAdd: () -> Unit = { showDialogComment.value = false }
    val onViewCommentAdd: () -> Unit = { showDialogComment.value = true }

    ShowADComment(
        showDialog = showDialogComment.value,
        themeId = themeId,
        topicId = topicId,
        onDismiss = { onDismissCommentAdd.invoke() },
        comminityVm = comminityVm,
        authVm = authenticationViewModel,
        navController = navController,
        user = user
    )

    ShowADAccount(//in topicscreen
        showDialog = showDialogAccount.value,
        onDismiss = onDismissAAD,
        viewModel = authenticationViewModel,
        navController = navController
    )

    Column(modifier = Modifier.fillMaxSize().background(background)) {
        ComminityHeader("Yorumlar", onBackClicked = {
            navController.popBackStack()
        }, onAddClicked = {
            onViewCommentAdd.invoke()
        }, onAccountClicked = {
            onViewAAD.invoke()
        })

        TopicCustomCard(
            content = comminityVm.topics.value.find { it.topicId == topicId }?.topic.toString(),
            modifier = Modifier.padding(4.dp),
            date = comminityVm.topics.value.find { it.topicId == topicId }?.date!!,
            userName = comminityVm.topics.value.find { it.topicId == topicId }?.user?.name.toString(),
            pinned = false
        )
        Divider(color = Color.Gray, modifier = Modifier.padding(start = 4.dp, end = 4.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(comments) {
                TopicCustomCard(
                    content = it.comment!!,
                    date = it.date!!,
                    modifier = Modifier,
                    userName = it.user?.name!!,
                    pinned =it.pinned ?: false
                )
            }
        }
    }


}


@Composable
fun ShowADComment(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    authVm: AuthenticationViewModel,
    navController: NavController,
    comminityVm: ComminityViewModel,
    themeId: String,
    topicId: String,
    user: User
) {
    val signIn = authVm.signIn.value
    val commentState = remember {
        mutableStateOf(TextFieldValue())
    }
    if (showDialog) {
        commentState.value=TextFieldValue()
        if (signIn) {
            AlertDialog(containerColor = adContainerColor,
                onDismissRequest = {
                    onDismiss.invoke()
                },
                confirmButton = {
                    Button(onClick = {
                        //soru sor
                        comminityVm.addComments(
                            comment = commentState.value.text,
                            themeId = themeId,
                            topicId = topicId,
                            user = user
                        )
                       // commentState.value=TextFieldValue()
                        onDismiss.invoke()
                    },colors = buttonColor()
                    ) {
                        Text(text = "Yorum yap")
                    }
                },
                title = { Text(text = "Yorum", color = line) },
                text = {
                    OutlinedTextField(value = commentState.value , maxLines = 3,onValueChange = {
                        commentState.value = it
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