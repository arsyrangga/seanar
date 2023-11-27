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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.rangga.seanar.R
import com.rangga.seanar.ui.navigation.homeLenderScreen
import com.rangga.seanar.ui.theme.primary
import com.rangga.seanar.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title:String, navController: NavController) {
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
                navController.navigate(homeLenderScreen)
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