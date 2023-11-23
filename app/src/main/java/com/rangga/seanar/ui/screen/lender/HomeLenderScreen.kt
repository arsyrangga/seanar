package com.rangga.seanar.ui.screen.borrower

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.rangga.seanar.ui.component.BottomBar
import com.rangga.seanar.ui.component.lender.BoxCardHome
import com.rangga.seanar.ui.component.lender.CardComponent
import com.rangga.seanar.ui.component.lender.ContainerCardLender
import com.rangga.seanar.ui.component.lender.HeaderLender
import com.rangga.seanar.ui.theme.primaryDark


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeLenderScreen(navController: NavController) {

    val datas = remember {
        mutableStateListOf(
            HomeCardParcel(
                imageLink = "https://picsum.photos/seed/430/600",
                title = "Modal Usaha Nelayan",
                description = "Kelompok Nelayan Mulyo",
                isVerified = true,
                minPinjaman = "Rp5.000.000,00",
                returns = "10%",
                tenor = "12 Bulan"
            ),
            HomeCardParcel(
                imageLink = "https://picsum.photos/seed/420/600",
                title = "Modal Usaha Nelayan",
                description = "Kelompok Nelayan Mulyo",
                isVerified = true,
                minPinjaman = "Rp5.000.000,00",
                returns = "10%",
                tenor = "12 Bulan"
            ),
            HomeCardParcel(
                imageLink = "https://picsum.photos/seed/400/600",
                title = "Modal Usaha Nelayan",
                description = "Kelompok Nelayan Mulyo",
                isVerified = true,
                minPinjaman = "Rp5.000.000,00",
                returns = "10%",
                tenor = "12 Bulan"
            ), HomeCardParcel(
                imageLink = "https://picsum.photos/seed/450/600",
                title = "Modal Usaha Nelayan",
                description = "Kelompok Nelayan Mulyo",
                isVerified = true,
                minPinjaman = "Rp5.000.000,00",
                returns = "10%",
                tenor = "12 Bulan"
            )
        )
    }

    Scaffold(bottomBar = { BottomBar() }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)

        ) {


            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    HeaderLender()
                    ContainerCardLender()
                    BoxCardHome()
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Pendanaan Menarik Untuk Anda!",
                            color = primaryDark,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 16.dp, bottom = 20.dp)

                        )
                    }
                }
                item {
                    FlowRow(modifier = Modifier.fillMaxWidth().padding(start =  20.dp, end = 20.dp,), horizontalArrangement = Arrangement.SpaceBetween) {
                        datas?.forEach {
                            CardComponent(it)
                        }
                    }

                }
            }
        }
    }
}

