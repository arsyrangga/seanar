package com.rangga.seanar.ui.screen.lender

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rangga.seanar.data.parcel.DonasiCardParcel
import com.rangga.seanar.data.parcel.PendanaanCardParcel
import com.rangga.seanar.ui.component.BottomBar
import com.rangga.seanar.ui.component.TopBar
import com.rangga.seanar.ui.component.lender.CardDonasi
import com.rangga.seanar.ui.component.lender.CardPendanaan
import com.rangga.seanar.ui.navigation.detailDonasiLenderScreen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DonasiLenderScreen(navController: NavController) {
    val datas = remember {
        mutableStateListOf(
            DonasiCardParcel(
                imageLink = "https://picsum.photos/seed/470/600",
                title = "Modal Usaha Nelayan",
                description = "Kelompok Nelayan Mulyo",
                isVerified = true,
                terkumpul = 700000000,
                target = 1000000000
            ),
            DonasiCardParcel(
                imageLink = "https://picsum.photos/seed/120/600",
                title = "Modal Usaha Nelayan",
                description = "Kelompok Nelayan Mulyo",
                isVerified = true,
                terkumpul = 200000000,
                target = 1000000000

            ),
            DonasiCardParcel(
                imageLink = "https://picsum.photos/seed/409/600",
                title = "Modal Usaha Nelayan",
                description = "Kelompok Nelayan Mulyo",
                isVerified = true,
                terkumpul = 500000000,
                target = 1000000000

            ), DonasiCardParcel(
                imageLink = "https://picsum.photos/seed/452/600",
                title = "Modal Usaha Nelayan",
                description = "Kelompok Nelayan Mulyo",
                isVerified = true,
                terkumpul = 400000000,
                target = 1000000000
            )
        )
    }
    Scaffold(bottomBar = { BottomBar(navController) }, topBar = { TopBar(title = "Donasi", navController = navController) }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)

        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp)
            ) {
                item {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        datas?.forEach {
                            CardDonasi(data = it, onClick = {navController.navigate(
                                detailDonasiLenderScreen)})
                        }
                    }

                }
            }
        }
    }
}