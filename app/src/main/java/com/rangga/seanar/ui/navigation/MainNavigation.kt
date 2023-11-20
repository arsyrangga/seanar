package com.rangga.seanar.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rangga.seanar.ui.screen.borrower.HomeLenderScreen
import com.rangga.seanar.ui.screen.onboarding.LoginScreen
import com.rangga.seanar.ui.screen.onboarding.RegisterBorrowerScreen
import com.rangga.seanar.ui.screen.onboarding.RegisterLenderScreen
import com.rangga.seanar.ui.screen.onboarding.SplashScreen

@Composable
fun SeanarNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController)
        }
        composable("login_screen") {
            LoginScreen(navController)
        }
        composable("register_lender_screen") {
            RegisterLenderScreen(navController)
        }
        composable("register_borrower_screen") {
            RegisterBorrowerScreen(navController)
        }
        composable("home_lender") { backStackEntry ->
//            val itemId = backStackEntry.arguments?.getString("itemId")
            HomeLenderScreen(navController)
        }
    }
}