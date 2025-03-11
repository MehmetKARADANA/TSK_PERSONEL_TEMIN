package com.mobile.tskpersonelteminapp.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.DestinationScreen
import com.mobile.tskpersonelteminapp.R
import com.mobile.tskpersonelteminapp.ui.components.CommonProgressBar
import com.mobile.tskpersonelteminapp.ui.theme.background
import com.mobile.tskpersonelteminapp.ui.theme.buttonColor
import com.mobile.tskpersonelteminapp.ui.theme.line
import com.mobile.tskpersonelteminapp.ui.theme.outlinedColor
import com.mobile.tskpersonelteminapp.utils.CheckSignedIn
import com.mobile.tskpersonelteminapp.utils.ObserveErrorMessage
import com.mobile.tskpersonelteminapp.utils.navigateTo
import com.mobile.tskpersonelteminapp.viewmodels.AuthenticationViewModel

@Composable
fun SignUpScreen(navController: NavController,viewModel: AuthenticationViewModel) {
    //mevcut kullanıcı varmı kontrol fonksiyonu
    val errorMessage by viewModel.errorMessage

    // Hata mesajını dinle ve göster
    ObserveErrorMessage(errorMessage)

    CheckSignedIn(viewModel,navController)
    val inProcess=viewModel.inProcess.value

    if (inProcess) {
        CommonProgressBar()
    } else {
        Box(modifier = Modifier.fillMaxSize().background(background)) {
            val focus = LocalFocusManager.current

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .clickable {
                        focus.clearFocus()
                    }, horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val nameState = remember {
                    mutableStateOf(TextFieldValue())
                }

                val emailState = remember {
                    mutableStateOf(TextFieldValue())
                }

                val passwordState = remember {
                    mutableStateOf(TextFieldValue())
                }

                val focusColumn = LocalFocusManager.current //bunu deneyeceğim

                Image(
                    painter = painterResource(R.drawable.user),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .width(140.dp)
                        .padding(top = 16.dp)
                        .padding(8.dp)
                )

                Text(
                    text = "Kayıt ol", color = line,modifier = Modifier
                        .padding(8.dp),
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(value = nameState.value, modifier = Modifier.padding(8.dp), maxLines = 1,
                    onValueChange = {
                        nameState.value = it
                    }, label = { Text(text = "Kullanıcı Adı", color = line) }, colors = outlinedColor()
                )

                OutlinedTextField(value = emailState.value,maxLines = 1,
                    modifier = Modifier.padding(8.dp),
                    onValueChange = { emailState.value = it },
                    label = { Text(text = "Mail Adresi", color = line) },colors = outlinedColor()
                )

                OutlinedTextField(
                    value = passwordState.value,maxLines = 1,
                    modifier = Modifier.padding(8.dp),
                    onValueChange = { passwordState.value = it },
                    label = { Text(text = "En az 6 karakter şifre", color = line) },colors = outlinedColor())

                Button(onClick = {
                    //kullanıcı kayıt
                    viewModel.signUp(name = nameState.value.text,password=passwordState.value.text, email = emailState.value.text)
                }, modifier = Modifier.padding(8.dp),colors = buttonColor()) {
                    Text(text = "Kayıt ol")
                }

                Text(
                    text = "Hesabın var mı? Giriş yap ->",
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            //navigate login
                            navigateTo(
                                navController = navController,
                                route = DestinationScreen.Login.route
                            )
                        },
                    color = Color.Black
                )
            }

        }
    }
}