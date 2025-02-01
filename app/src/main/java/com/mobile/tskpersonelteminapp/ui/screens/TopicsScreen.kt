package com.mobile.tskpersonelteminapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.DestinationScreen
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenu
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenuItem
import com.mobile.tskpersonelteminapp.ui.components.ComminityHeader
import com.mobile.tskpersonelteminapp.utils.navigateTo
import com.mobile.tskpersonelteminapp.viewmodels.AuthenticationViewModel
import com.mobile.tskpersonelteminapp.viewmodels.ComminityViewModel

@Composable
fun TopicsScreen(navController: NavController,commnityVm: ComminityViewModel,authViewModel: AuthenticationViewModel){
    // val signIn = viewModel.signIn.value

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

    ShowADAccount(
        onDismiss = onDismissAAD,
        viewModel = authViewModel,
        showDialog = showDialogAccount.value,
        navController = navController
    )
    ShowADComment(
        showDialog = showDialogComment.value,
        onDismiss = onDismissCommentAdd,
        viewModel = authViewModel,
        navController = navController
    )
    Column(modifier = Modifier.fillMaxSize()) {
        ComminityHeader("Konular", onBackClicked = {
            navController.popBackStack()
        }, onAccountClicked = {
            onViewAAD.invoke()
        }, onAddClicked = {
            onViewCommentAdd.invoke()
        })
        //topics
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Topics")
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
            AlertDialog(
                onDismissRequest = {
                    onDismiss.invoke()
                },
                confirmButton = {
                    Button(onClick = {
                        navigateTo(
                            navController = navController,
                            DestinationScreen.Profile.route
                        )
                    }) {
                        Text(text = "Profilime git")
                    }
                },
                title = { Text(text = "Profilim") },
                text = { Text(text = "Profili görüntülemek için tıklayın.") })
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
                text = { Text(text = "Henüz giriş yapmadınız! Giriş yapmak için tıklayın.") })
        }
    }
}

@Composable
fun ShowADComment(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    viewModel: AuthenticationViewModel,
    navController: NavController
) {
    val signIn = viewModel.signIn.value
    if (showDialog) {
        if (signIn) {
            AlertDialog(
                onDismissRequest = {
                    onDismiss.invoke()
                },
                confirmButton = {
                    Button(onClick = {
                        navigateTo(
                            navController = navController,
                            DestinationScreen.Profile.route
                        )
                    }) {
                        Text(text = "Soru sor")
                    }
                },
                title = { Text(text = "Soru") },
                text = { Text(text = "Soruu...") })
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