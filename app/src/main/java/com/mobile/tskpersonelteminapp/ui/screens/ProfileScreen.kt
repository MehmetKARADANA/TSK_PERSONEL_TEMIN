package com.mobile.tskpersonelteminapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.DestinationScreen
import com.mobile.tskpersonelteminapp.R
import com.mobile.tskpersonelteminapp.ui.components.CommonProgressBar
import com.mobile.tskpersonelteminapp.ui.components.ProfileHeader
import com.mobile.tskpersonelteminapp.ui.theme.background
import com.mobile.tskpersonelteminapp.utils.ObserveErrorMessage
import com.mobile.tskpersonelteminapp.utils.navigateTo
import com.mobile.tskpersonelteminapp.viewmodels.AuthenticationViewModel

@Composable
fun ProfileScreen(navController: NavController, viewModel: AuthenticationViewModel) {

    val errorMessageAuth by viewModel.errorMessage

    ObserveErrorMessage(errorMessageAuth)

    val signIn = viewModel.signIn.value

    val inProcess = viewModel.inProcess.value
    val userData = viewModel.userData.value

    BackHandler {
        navController.popBackStack()
    }

    if (inProcess) {

        CommonProgressBar()
    } else {
        val name by remember {
            mutableStateOf(
                userData?.name ?: ""
            )
        }

        val email by remember {
            mutableStateOf(
                userData?.email ?: ""
            )
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .background(background)) {
            ProfileHeader(
                onBackClicked = { navController.popBackStack() },
                onLogoutClicked = {
                    viewModel.logout()
                    navController.popBackStack()
                }, "Profilim"
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
                    .padding(bottom = 150.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileContent(name = name, email = email)
                if (!signIn) {
                    Text(
                        text = "\uD83D\uDC49 Giriş yapmak için tıklayın.",
                        color = Color.Black,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                navigateTo(navController, DestinationScreen.Login.route)
                            })
                }

            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(name: String?, email: String?) {

    Image(
        painter = painterResource(R.drawable.user),
        contentDescription = "profile",
        modifier = Modifier
            .width(140.dp)
            .padding(top = 40.dp)
            .padding(8.dp)
    )

    Text(
        text = "Profilim", modifier = Modifier
            .padding(8.dp),
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold
    )


    OutlinedTextField(
        value = name!!,
        modifier = Modifier.padding(8.dp),
        enabled = false,
        onValueChange = {},
        label = { Text(text = "Kullanıcı Adı") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            disabledTextColor = Color.Black, // Metin rengi
            disabledLabelColor = Color.Gray, // Label rengi
            disabledBorderColor = Color.Black// Çerçeve rengi
        )
    )


    OutlinedTextField(
        value = email!!,
        modifier = Modifier.padding(8.dp),
        enabled = false,
        onValueChange = {},
        label = { Text(text = "Mail Adresi") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            disabledTextColor = Color.Black, // Metin rengi
            disabledLabelColor = Color.Gray, // Label rengi
            disabledBorderColor = Color.Black // Çerçeve rengi
        )
    )


}
