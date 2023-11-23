package com.rangga.seanar.ui.screen.lender

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.rangga.seanar.ui.component.BottomBar

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DonasiLenderScreen(navController: NavController) {
    Scaffold(bottomBar = { BottomBar(navController) }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)

        ) {

        }
    }
}