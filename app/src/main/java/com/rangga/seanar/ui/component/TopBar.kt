package com.rangga.seanar.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rangga.seanar.R
import com.rangga.seanar.ui.navigation.detailDonasiBorrowerScreen
import com.rangga.seanar.ui.navigation.detailDonasiLenderScreen
import com.rangga.seanar.ui.navigation.detailPendanaanLenderScreen
import com.rangga.seanar.ui.navigation.donasiBorrowerScreen
import com.rangga.seanar.ui.navigation.donasiLenderScreen
import com.rangga.seanar.ui.navigation.homeBorrowerScreen
import com.rangga.seanar.ui.navigation.homeLenderScreen
import com.rangga.seanar.ui.navigation.pendanaanLenderScreen
import com.rangga.seanar.ui.navigation.profileBorrowerScreen
import com.rangga.seanar.ui.navigation.profileLenderScreen
import com.rangga.seanar.ui.theme.primary
import com.rangga.seanar.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String, navController: NavController, role: String = "lender") {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    var currentRoute by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = true) {
        currentRoute = currentBackStackEntry?.destination?.route.toString()
    }
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = primary,
            titleContentColor = white,
        ),
        title = {
            Text(
                title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                if (role == "lender") {
                    if (currentRoute == homeLenderScreen || currentRoute == pendanaanLenderScreen || currentRoute == donasiLenderScreen || currentRoute == profileLenderScreen) {
                        navController.navigate(homeLenderScreen)
                    } else {
                        navController.popBackStack()
                    }
                } else {
                    if (currentRoute == homeBorrowerScreen || currentRoute == donasiBorrowerScreen || currentRoute == profileBorrowerScreen || currentRoute == detailPendanaanLenderScreen) {
                        navController.navigate(homeBorrowerScreen)
                    } else if (currentRoute == detailDonasiBorrowerScreen) {
                        navController.navigate(donasiBorrowerScreen)
                    } else {
                        navController.popBackStack()
                    }
                }

            }) {
                Icon(
                    tint = white,
                    painter = painterResource(id = R.drawable.chevron_back),
                    contentDescription = "Localized description"
                )
            }
        },

        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    )
}