package com.mobile.tskpersonelteminapp.ui.screens

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.ui.components.BackHeader
import com.mobile.tskpersonelteminapp.ui.components.CommonProgressBar
import com.mobile.tskpersonelteminapp.utils.ObserveErrorMessage
import com.mobile.tskpersonelteminapp.viewmodels.SettingsViewModel
import com.mobile.tskpersonelteminapp.viewmodels.SuggestionViewModel

@Composable
fun SuggestionScreen(navController: NavController,viewModel: SuggestionViewModel) {
   val errorMessage by viewModel.errorMessage

    val inProcess = viewModel.inProcess.value
    ObserveErrorMessage(errorMessage)

    if (inProcess){
        CommonProgressBar()
    }else{
    Column(modifier = Modifier.fillMaxSize()) {
        BackHeader(onBackClicked = {navController.popBackStack()}, headerText = "Görüş/Öneri")
        Column (modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            val suggestion = remember {
                mutableStateOf(TextFieldValue())
            }
            val email = remember {
                mutableStateOf(TextFieldValue())
            }

            OutlinedTextField(value =suggestion.value , modifier = Modifier.padding(8.dp),label = { Text(text = "Öneri/Görüş") } , onValueChange = {
                suggestion.value=it
            })
            Spacer(modifier = Modifier.padding(8.dp))
            OutlinedTextField(value =email.value , modifier = Modifier.padding(8.dp),label = { Text(text = "Mail Adresi") } , onValueChange = {
                email.value=it
            })
            Spacer(modifier = Modifier.padding(8.dp))
            Button(onClick = {
                viewModel.addSuggestion(sugges = suggestion.value.text, email = email.value.text)
                suggestion.value=TextFieldValue()
                email.value=TextFieldValue()
            },modifier = Modifier.padding(8.dp)) {
                Text(text = "Gönder")
            }

            Spacer(modifier = Modifier.padding(24.dp))

            Text(text ="Geri bildirimleriniz için teşekkür ederiz.", modifier = Modifier.padding(bottom = 8.dp))
        }
        Spacer(modifier = Modifier.padding(32.dp))
    }}
}