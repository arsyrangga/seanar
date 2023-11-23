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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rangga.seanar.R
import com.rangga.seanar.ui.navigation.donasiLenderScreen
import com.rangga.seanar.ui.navigation.homeLenderScreen
import com.rangga.seanar.ui.navigation.pendanaanLenderScreen
import com.rangga.seanar.ui.navigation.profileLenderScreen

data class BottomBarItem(val title: String, val icon: Painter, val nav: String)

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        var currentRoute = remember {
            mutableStateOf("")
        }
        LaunchedEffect(key1 = true) {
            currentRoute.value = currentBackStackEntry?.destination?.route.toString()
        }
        val navigationItems = listOf(
            BottomBarItem(
                title = stringResource(R.string.beranda),
                icon = painterResource(id = R.drawable.home_icon),
                nav = homeLenderScreen
            ),
            BottomBarItem(
                title = stringResource(R.string.pendanaan),
                icon = painterResource(id = R.drawable.funds),
                nav = pendanaanLenderScreen
            ),
            BottomBarItem(
                title = stringResource(R.string.donasi),
                icon = painterResource(id = R.drawable.donate),
                nav = donasiLenderScreen
            ),
            BottomBarItem(
                title = stringResource(R.string.profile),
                icon = painterResource(id = R.drawable.profile_icon),
                nav = profileLenderScreen
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
                selected = it.nav == currentRoute.value,
                onClick = {
                    navController.navigate(it.nav){
                        popUpTo(currentRoute.value) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}