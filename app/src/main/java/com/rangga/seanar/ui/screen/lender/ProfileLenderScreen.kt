package com.rangga.seanar.ui.screen.lender

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rangga.seanar.R
import com.rangga.seanar.helper.TokenDatastore
import com.rangga.seanar.ui.component.BottomBar
import com.rangga.seanar.ui.component.TopBar
import com.rangga.seanar.ui.component.lender.HeaderLender
import com.rangga.seanar.ui.component.lender.HeaderProfileLender
import com.rangga.seanar.ui.component.lender.ProfileCard
import com.rangga.seanar.ui.navigation.loginScreen
import com.rangga.seanar.ui.navigation.profileLenderScreen
import com.rangga.seanar.ui.theme.black
import com.rangga.seanar.ui.theme.danger_bold
import com.rangga.seanar.ui.theme.primary
import com.rangga.seanar.ui.theme.secondary
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileLenderScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sessionManager = TokenDatastore(context = context)
    val userData = sessionManager.getDetail()
    Scaffold(bottomBar = { BottomBar(navController) },
        topBar = { TopBar(title = "Profile", navController = navController) }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            HeaderProfileLender(data = userData)
            Column(modifier = Modifier.padding(vertical = 16.dp)) {
                ProfileCard(
                    color = black,
                    icon = R.drawable.profile_icon_primary,
                    title = "Atur Profile",
                    onClick = {})
                ProfileCard(
                    color = black,
                    icon = R.drawable.profile_riwayat_icon,
                    title = "Riwayat Transaksi",
                    onClick = {})
                ProfileCard(
                    color = black,
                    icon = R.drawable.profile_privasi_icon,
                    title = "Kebijakan Privasi",
                    onClick = {})
                ProfileCard(
                    color = black,
                    icon = R.drawable.profile_help_icon,
                    title = "Pusat Bantuan",
                    onClick = {})
                ProfileCard(
                    color = black,
                    icon = R.drawable.profile_setting_icon,
                    title = "Pengaturan Akun",
                    onClick = {})
                ProfileCard(
                    color = danger_bold,
                    icon = R.drawable.profile_logout_icon,
                    title = "Keluar",
                    onClick = {
                        coroutineScope.launch {
                            sessionManager.clear()
                            navController.navigate(
                                loginScreen
                            ) {
                                popUpTo(profileLenderScreen) {
                                    inclusive = true
                                }
                            }
                        }

                    })
            }

        }
    }
}