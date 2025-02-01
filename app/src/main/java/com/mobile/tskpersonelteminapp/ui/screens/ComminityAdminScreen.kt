package com.mobile.tskpersonelteminapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.DestinationScreen
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenu
import com.mobile.tskpersonelteminapp.ui.components.BottomNavigationMenuItem
import com.mobile.tskpersonelteminapp.ui.components.ComminityHeader
import com.mobile.tskpersonelteminapp.utils.navigateTo
import com.mobile.tskpersonelteminapp.viewmodels.AuthenticationViewModel
import com.mobile.tskpersonelteminapp.viewmodels.ComminityViewModel

@Composable
fun ComminityAdminScreen(viewModel: ComminityViewModel) {
    val inProcess = viewModel.inProcess.value

    if (false) {
        //commonprogresbar
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                val themeState = remember {
                    mutableStateOf(TextFieldValue())
                }

                Text(
                    text = "Comminity Admin",
                    modifier = Modifier.padding(8.dp),
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = themeState.value,
                    modifier = Modifier.padding(8.dp),
                    onValueChange = {
                        themeState.value = it
                    },
                    label = { Text(text = "Genel Konular") })

                Button(onClick = {
                    viewModel.addTheme(themeState.value.text)

                }, modifier = Modifier.padding(8.dp)) {
                    Text(text = "Genel Konu Ekle")
                }

            }
        }
    }
}
/*
@Composable
fun ComminityAdminScreen(navController: NavController, viewModel: AuthenticationViewModel) {

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
        viewModel = viewModel,
        showDialog = showDialogAccount.value,
        navController = navController
    )
    ShowADComment(
        showDialog = showDialogComment.value,
        onDismiss = onDismissCommentAdd,
        viewModel = viewModel,
        navController = navController
    )
    Column(modifier = Modifier.fillMaxSize()) {
        ComminityHeader("Comminity", onBackClicked = {
        }, onAccountClicked = {
            onViewAAD.invoke()
        }, onAddClicked = {
            onViewCommentAdd.invoke()
        })
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Comminity")
        }
        BottomNavigationMenu(
            selectedItem = BottomNavigationMenuItem.COMMINITY,
            navController = navController
        )
    }

}
*/
/*
*  OutlinedTextField(value = question.value, onValueChange = {
                        question.value=it
                    })*/