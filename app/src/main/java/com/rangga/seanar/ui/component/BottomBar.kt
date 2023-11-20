package com.rangga.seanar.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.rangga.seanar.R

data class BottomBarItem(val title: String, val icon: Painter)

@Composable
fun BottomBar(
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        val navigationItems = listOf(
            BottomBarItem(
                title = stringResource(R.string.beranda),
                icon = painterResource(id = R.drawable.home_icon)
            ),
            BottomBarItem(
                title = stringResource(R.string.pendanaan),
                icon = painterResource(id = R.drawable.funds)
            ),
            BottomBarItem(
                title = stringResource(R.string.donasi),
                icon = painterResource(id = R.drawable.donate)
            ),
            BottomBarItem(
                title = stringResource(R.string.profile),
                icon = painterResource(id = R.drawable.profile_icon)
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
                selected = it.title == navigationItems[0].title,
                onClick = {}
            )
        }
    }
}