package com.rangga.seanar.ui.screen.borrower

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rangga.seanar.R
import com.rangga.seanar.data.parcel.HomeCardParcel
import com.rangga.seanar.helper.TokenDatastore
import com.rangga.seanar.ui.component.BottomBar
import com.rangga.seanar.ui.component.BottomBarBorrower
import com.rangga.seanar.ui.component.TopBar
import com.rangga.seanar.ui.component.borrower.HeaderProfileBorrower
import com.rangga.seanar.ui.component.lender.HeaderProfileLender
import com.rangga.seanar.ui.component.lender.ProfileCard
import com.rangga.seanar.ui.navigation.loginScreen
import com.rangga.seanar.ui.navigation.profileBorrowerScreen
import com.rangga.seanar.ui.navigation.profileLenderScreen
import com.rangga.seanar.ui.theme.black
import com.rangga.seanar.ui.theme.danger_bold
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProfileBorrowerScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sessionManager = TokenDatastore(context = context)
    Scaffold(bottomBar = { BottomBarBorrower(navController) },
        topBar = {
            TopBar(
                title = "Profile",
                navController = navController,
                role = "borrower"
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            HeaderProfileBorrower()
            Column(modifier = Modifier.padding(vertical = 16.dp)) {
                ProfileCard(
                    color = black,
                    icon = R.drawable.profile_icon_primary,
                    title = "Atur Profile",
                    onClick = {})
                ProfileCard(
                    color = black,
                    icon = R.drawable.profile_riwayat_icon,
                    title = "Edit Data",
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
                                popUpTo(profileBorrowerScreen) {
                                    inclusive = true
                                }
                            }
                        }
                    })
            }

        }
    }
}