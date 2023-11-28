package com.rangga.seanar.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rangga.seanar.R
import com.rangga.seanar.ui.navigation.donasiBorrowerScreen
import com.rangga.seanar.ui.navigation.donasiLenderScreen
import com.rangga.seanar.ui.navigation.homeBorrowerScreen
import com.rangga.seanar.ui.navigation.homeLenderScreen
import com.rangga.seanar.ui.navigation.pendanaanLenderScreen
import com.rangga.seanar.ui.navigation.profileBorrowerScreen
import com.rangga.seanar.ui.navigation.profileLenderScreen


@Composable
fun BottomBarBorrower(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        var currentRoute by remember {
            mutableStateOf("")
        }
        LaunchedEffect(key1 = true) {
            currentRoute = currentBackStackEntry?.destination?.route.toString()
        }

        val navigationItems = listOf(
            BottomBarItem(
                title = stringResource(R.string.pinjaman),
                icon = painterResource(id = R.drawable.funds),
                nav = homeBorrowerScreen
            ),
            BottomBarItem(
                title = stringResource(R.string.donasi),
                icon = painterResource(id = R.drawable.donate),
                nav = donasiBorrowerScreen
            ),
            BottomBarItem(
                title = stringResource(R.string.profile),
                icon = painterResource(id = R.drawable.profile_icon),
                nav = profileBorrowerScreen
            ),
        )


        navigationItems.map {
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = it.icon,
                        contentDescription = it.title
                    )
                },
                label = {
                    Text(it.title)
                },
                selected = it.nav == currentRoute,
                onClick = {
                    navController.navigate(it.nav) {
                        popUpTo(currentRoute) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}