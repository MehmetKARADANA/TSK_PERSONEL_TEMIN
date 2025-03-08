package com.mobile.tskpersonelteminapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mobile.tskpersonelteminapp.R
import com.mobile.tskpersonelteminapp.ui.theme.buttonColor
import kotlinx.coroutines.launch


@Composable
fun OnboardingDialog(onDismiss: () -> Unit) {
    val onboardingImages = listOf(
        R.drawable.ss1r,
        R.drawable.ss12,
        R.drawable.ss2r,
        R.drawable.ss3r,
        R.drawable.ss31,
        R.drawable.ss32,
        R.drawable.ss4
    )
    val pagerState = rememberPagerState(pageCount = { onboardingImages.size })
    val coroutineScope = rememberCoroutineScope()

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPager(state = pagerState) { page ->
                    Image(
                        painter = painterResource(id = onboardingImages[page]),
                        contentDescription = "Onboarding Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(onboardingImages.size) { index ->
                        Spacer(modifier = Modifier.padding(2.dp))
                        val color = if (pagerState.currentPage == index) Color.Black else Color.Gray
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(color, CircleShape)
                                .padding(4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (pagerState.currentPage == onboardingImages.lastIndex) {
                            onDismiss()
                        } else {
                            // animated asenkron olduğu için c.scope kullandım
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    }, colors = buttonColor(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = if (pagerState.currentPage == onboardingImages.lastIndex) "Tamam" else "İleri")
                }
            }
        }
    }
}