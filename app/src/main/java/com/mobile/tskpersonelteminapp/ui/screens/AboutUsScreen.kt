package com.mobile.tskpersonelteminapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.simulateHotReload
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mobile.tskpersonelteminapp.ui.components.BackHeader
import com.mobile.tskpersonelteminapp.ui.theme.background
import com.mobile.tskpersonelteminapp.ui.theme.line
import com.mobile.tskpersonelteminapp.ui.theme.primaryColor

@Composable
fun AboutUsScreen(navController: NavController) {

    BackHandler {
        navController.popBackStack()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        BackHeader(onBackClicked = {
            navController.popBackStack()
        }, "Hakkımızda")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                item {
                    // About Us Title (Bold)
                    Text(
                        text = "Hakkımızda",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold, // Bold title
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = line
                    )
                }

                item {
                    // About Us Content
                    Text(
                        text = "TSK Personel Temin Uygulaması, Türk Silahlı Kuvvetleri'ne personel alımı süreçlerini takip etmenizi kolaylaştıran bir mobil uygulamadır. Bu uygulama sayesinde, güncel alım duyurularını takip edebilir, başvuru şartlarını inceleyebilir ve sürecinizin hangi aşamada olduğunu kolayca öğrenebilirsiniz.",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 16.dp),
                        color = line
                    )
                }

                item {
                    // Mission Title (Bold)
                    Text(
                        text = "Misyonumuz",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold, // Bold title
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = line
                    )
                }

                item {
                    // Mission Content
                    Text(
                        text = "Adayların, TSK personel alım süreçlerine hızlı ve güvenilir bir şekilde erişim sağlamasına yardımcı olmak.",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 16.dp),
                        color = line
                    )
                }

                item {
                    // Vision Title (Bold)
                    Text(
                        text = "Vizyonumuz",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold, // Bold title
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = line
                    )
                }

                item {
                    // Vision Content
                    Text(
                        text = "Teknolojiyi kullanarak şeffaf, erişilebilir ve kullanıcı dostu bir personel temin süreci oluşturmak.",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 16.dp),
                        color = line
                    )
                }

                item {
                    // Features Title (Bold)
                    Text(
                        text = "Özelliklerimiz",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold, // Bold title
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = line
                    )
                }

                item {
                    // Features Content
                    Text(
                        text = "✅ Güncel personel alım duyurularını görüntüleyin.\n✅ Başvuru şartlarını ve gerekli belgeleri öğrenin.\n✅ Başvuru sürecinizi adım adım takip edin.\n✅ Sonuçlar ve mülakat tarihleri hakkında anlık bildirimler alın.",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 16.dp),
                        color = line
                    )
                }

                item {
                    // Contact and Support Title (Bold)
                    Text(
                        text = "İletişim ve Destek",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold, // Bold title
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = line
                    )
                }

                item {
                    // Contact and Support Content
                    Text(
                        text = "Resmî duyurular ve güncellemeler için TSK Personel Temin Merkezi resmî web sitesini ziyaret edebilirsiniz:",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = line
                    )
                }

                item {
                    // Official website link
                    SelectionContainer {
                        Text(
                            text = "personeltemin.msb.gov.tr",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 16.dp),
                            color = line
                        )
                    }
                }

                item {
                    // Support Phone and Email (Clickable)
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "📞 Destek Hattı: (Varsa destek numarası)",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 8.dp),
                            color = line
                        )
                        SelectionContainer {
                            Text(
                                text = AnnotatedString("✉️ E-Posta: karadanam519@gmail.com"),
                                fontSize = 16.sp,
                                modifier = Modifier.padding(bottom = 8.dp),
                                color = line
                            )
                        }
                    }
                }

                item {
                    // App Version and Update Info
                    Text(
                        text = "📅 Versiyon: 1.0.0",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = line
                    )
                }

                item {
                    Text(
                        text = "📅 Son Güncelleme: Şubat 2025",
                        fontSize = 16.sp,
                        color = line
                    )
                }
            }
        }
    }
}