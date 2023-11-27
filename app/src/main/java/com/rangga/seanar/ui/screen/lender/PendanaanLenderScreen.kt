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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rangga.seanar.data.parcel.HomeCardParcel
import com.rangga.seanar.data.parcel.PendanaanCardParcel
import com.rangga.seanar.ui.component.BottomBar
import com.rangga.seanar.ui.component.TopBar
import com.rangga.seanar.ui.component.lender.BoxCardHome
import com.rangga.seanar.ui.component.lender.CardComponent
import com.rangga.seanar.ui.component.lender.CardPendanaan
import com.rangga.seanar.ui.component.lender.ContainerCardLender
import com.rangga.seanar.ui.component.lender.HeaderLender
import com.rangga.seanar.ui.navigation.detailPendanaanLenderScreen
import com.rangga.seanar.ui.theme.primaryDark

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PendanaanLenderScreen(navController: NavController) {
    val datas = remember {
        mutableStateListOf(
            PendanaanCardParcel(
                imageLink = "https://picsum.photos/seed/470/600",
                title = "Modal Usaha Nelayan",
                description = "Kelompok Nelayan Mulyo",
                isVerified = true,
                minPinjaman = 5000000,
                returns = "10%",
                tenor = "12 Bulan",
                target = 300000000
            ),
            PendanaanCardParcel(
                imageLink = "https://picsum.photos/seed/120/600",
                title = "Modal Usaha Nelayan",
                description = "Kelompok Nelayan Mulyo",
                isVerified = true,
                minPinjaman = 5000000,
                returns = "10%",
                tenor = "12 Bulan",
                target = 300000000

            ),
            PendanaanCardParcel(
                imageLink = "https://picsum.photos/seed/409/600",
                title = "Modal Usaha Nelayan",
                description = "Kelompok Nelayan Mulyo",
                isVerified = true,
                minPinjaman = 5000000,
                returns = "10%",
                tenor = "12 Bulan",
                target = 300000000

            ), PendanaanCardParcel(
                imageLink = "https://picsum.photos/seed/452/600",
                title = "Modal Usaha Nelayan",
                description = "Kelompok Nelayan Mulyo",
                isVerified = true,
                minPinjaman = 5000000,
                returns = "10%",
                tenor = "12 Bulan",
                target = 300000000

            )
        )
    }

    Scaffold(
        bottomBar = { BottomBar(navController) },
        topBar = { TopBar(title = "Mitra Pendanaan", navController = navController) },
    ) { innerPadding ->
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
                            CardPendanaan(data = it, onClick = {
                                navController.navigate(
                                    detailPendanaanLenderScreen
                                )
                            })
                        }
                    }

                }
            }
        }
    }
}