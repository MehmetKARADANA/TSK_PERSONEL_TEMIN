package com.mobile.tskpersonelteminapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.DestinationScreen
import com.mobile.tskpersonelteminapp.data.models.User
import com.mobile.tskpersonelteminapp.ui.components.ComminityHeader
import com.mobile.tskpersonelteminapp.utils.navigateTo
import com.mobile.tskpersonelteminapp.viewmodels.AuthenticationViewModel
import com.mobile.tskpersonelteminapp.viewmodels.ComminityViewModel

@Composable
fun CommentScreen(
    navController: NavController,
    cominityVm: ComminityViewModel,
    authenticationViewModel: AuthenticationViewModel,
    themeId: String,
    topicId: String
) {
    LaunchedEffect(Unit) {
        cominityVm.getComments(themeId = themeId, topicId = topicId)
    }

    val comments = cominityVm.comments.value

    val userData =authenticationViewModel.userData.value
    val user  =User(name = userData?.name, userId = userData?.userId, email = userData?.email)


    val showDialogAccount = remember {
        mutableStateOf(false)
    }

    val onDismissAAD: () -> Unit = { showDialogAccount.value = false }//AccountAlertDialog
    val onViewAAD: () -> Unit = { showDialogAccount.value = true }

    val showDialogComment = remember {
        mutableStateOf(false)
    }

    val onDismissCommentAdd : () -> Unit ={showDialogComment.value=false}
    val onViewCommentAdd : () -> Unit = {showDialogComment.value=true}

    ShowADComment(showDialog = showDialogComment.value, themeId = themeId, topicId = topicId,onDismiss = {onDismissCommentAdd.invoke()}, commnityVm = cominityVm, authVm = authenticationViewModel, navController = navController, user =user )

    ShowADAccount(//in topicscreen
        showDialog = showDialogAccount.value,
        onDismiss = onDismissAAD,
        viewModel = authenticationViewModel,
        navController = navController
    )

    Column(modifier = Modifier.fillMaxSize()) {
        ComminityHeader("Yorumlar", onBackClicked = {
            navController.popBackStack()
        }, onAddClicked = {
            onViewCommentAdd.invoke()
        }, onAccountClicked = {
            onViewAAD.invoke()
        })
        Text(text = cominityVm.topics.value.find { it.topicId == topicId }?.topic.toString())
        Text("-----------")
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(comments) {
                Text(text = it.comment!!)
                Text(text = it.user?.name!!)
                Text("-------")
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
    commnityVm: ComminityViewModel,
    themeId : String,
    topicId: String,
    user: User
) {
    val signIn = authVm.signIn.value

    val commentState = remember {
        mutableStateOf(TextFieldValue())
    }
    if (showDialog) {
        if (signIn) {
            AlertDialog(
                onDismissRequest = {
                    onDismiss.invoke()
                },
                confirmButton = {
                    Button(onClick = {
                        //soru sor
                        commnityVm.addComments(comment = commentState.value.text,themeId=themeId,topicId=topicId,user=user)
                        onDismiss.invoke()
                    }) {
                        Text(text = "Yorum yap")
                    }
                },
                title = { Text(text = "Yorum") },
                text = { OutlinedTextField(value = commentState.value, onValueChange = {
                    commentState.value=it
                }) })
        } else {
            AlertDialog(
                onDismissRequest = {
                    onDismiss.invoke()
                },
                confirmButton = {
                    Button(onClick = {
                        navigateTo(
                            navController = navController,
                            DestinationScreen.SignUp.route
                        )
                    }) {
                        Text(text = "Giriş yap")
                    }
                },
                title = { Text(text = "Giriş Yap") },
                text = { Text(text = "Giriş yapmadan soru soramazsınız. Giriş yapmak için tıklayın.") })
        }
    }
}