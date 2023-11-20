package com.rangga.seanar.ui.screen.borrower

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.rangga.seanar.ui.component.BottomBar

@Composable
fun HomeLenderScreen(navController: NavController) {
    Scaffold(bottomBar = { BottomBar() }) { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            Text(text = "HOMELENDER")
        }
    }
}