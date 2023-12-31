package com.rangga.seanar.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.util.query
import com.rangga.seanar.ui.screen.borrower.CreateDonasiBorrowerScreen
import com.rangga.seanar.ui.screen.borrower.CreateFundingBorrowerScreen
import com.rangga.seanar.ui.screen.borrower.DonasiBorrowerScreen
import com.rangga.seanar.ui.screen.borrower.HomeBorrowerScreen
import com.rangga.seanar.ui.screen.borrower.ProfileBorrowerScreen
import com.rangga.seanar.ui.screen.lender.DetailDonasiBorrowerScreen
import com.rangga.seanar.ui.screen.lender.DetailDonasiLenderScreen
import com.rangga.seanar.ui.screen.lender.DetailPendanaanBorrowerScreen
import com.rangga.seanar.ui.screen.lender.DetailPendanaanLenderScreen
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

    NavHost(navController, startDestination = splashScreen) {
        //Onboarding
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
        //Onboarding

        //Lender
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
        composable("${detailPendanaanLenderScreen}/{query}") {backStackEntry->
            val route = requireNotNull(backStackEntry.arguments?.getString("query"))
            DetailPendanaanLenderScreen(navController, query = route)
        }
        composable("${detailDonasiLenderScreen}/{query}") {backStackEntry ->
            val route = requireNotNull(backStackEntry.arguments?.getString("query"))
            DetailDonasiLenderScreen(navController, query = route)
        }
        //Lender

        //Borrower
        composable(homeBorrowerScreen) {
            HomeBorrowerScreen(navController)
        }
        composable(donasiBorrowerScreen) {
            DonasiBorrowerScreen(navController)
        }
        composable(profileBorrowerScreen) {
            ProfileBorrowerScreen(navController)
        }
        composable(createFundingBorrowerScreen){
            CreateFundingBorrowerScreen(navController)

        }
        composable(createDonasiBorrowerScreen){
            CreateDonasiBorrowerScreen(navController)
        }

        composable("${detailPendanaanBorrowerScreen}/{query}") {backStackEntry->
            val route = requireNotNull(backStackEntry.arguments?.getString("query"))
            DetailPendanaanBorrowerScreen(navController, query = route)
        }
        composable("${detailDonasiBorrowerScreen}/{query}") {backStackEntry ->
            val route = requireNotNull(backStackEntry.arguments?.getString("query"))
            DetailDonasiBorrowerScreen(navController, query = route)
        }
        //Borrower
    }
}