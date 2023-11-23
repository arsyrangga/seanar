package com.rangga.seanar.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rangga.seanar.ui.screen.lender.DonasiLenderScreen
import com.rangga.seanar.ui.screen.lender.HomeLenderScreen
import com.rangga.seanar.ui.screen.lender.PendanaanLenderScreen
import com.rangga.seanar.ui.screen.lender.ProfileLenderScreen
import com.rangga.seanar.ui.screen.onboarding.LoginScreen
import com.rangga.seanar.ui.screen.onboarding.RegisterBorrowerScreen
import com.rangga.seanar.ui.screen.onboarding.RegisterLenderScreen
import com.rangga.seanar.ui.screen.onboarding.SplashScreen

@Composable
fun SeanarNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = homeLenderScreen) {
        composable(splashScreen) {
            SplashScreen(navController)
        }
        composable(loginScreen) {
            LoginScreen(navController)
        }
        composable(registerLenderScreen) {
            RegisterLenderScreen(navController)
        }
        composable(registerBorrowerScreen) {
            RegisterBorrowerScreen(navController)
        }
        composable(homeLenderScreen) { backStackEntry ->
//            val itemId = backStackEntry.arguments?.getString("itemId")
            HomeLenderScreen(navController)
        }
        composable(pendanaanLenderScreen) {
            PendanaanLenderScreen(navController)
        }
        composable(donasiLenderScreen) {
            DonasiLenderScreen(navController)
        }
        composable(profileLenderScreen) {
            ProfileLenderScreen(navController)
        }
    }
}