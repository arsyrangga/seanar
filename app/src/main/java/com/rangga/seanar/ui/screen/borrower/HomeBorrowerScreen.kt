package com.rangga.seanar.ui.screen.borrower

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rangga.seanar.data.parcel.HomeCardParcel
import com.rangga.seanar.data.parcel.PendanaanCardParcel
import com.rangga.seanar.data.parcel.PinjamanCardParcel
import com.rangga.seanar.ui.component.BottomBar
import com.rangga.seanar.ui.component.BottomBarBorrower
import com.rangga.seanar.ui.component.borrower.BoxCardBorrower
import com.rangga.seanar.ui.component.borrower.CardPinjamanBorrower
import com.rangga.seanar.ui.component.borrower.ContainerCardBorrower
import com.rangga.seanar.ui.component.borrower.HeaderBorrower
import com.rangga.seanar.ui.component.lender.CardPendanaan
import com.rangga.seanar.ui.navigation.detailPendanaanLenderScreen
import com.rangga.seanar.ui.theme.primaryDark

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeBorrowerScreen(navController: NavController) {

    val datas = remember {
        mutableStateListOf(
            PinjamanCardParcel(
                imageLink = "https://picsum.photos/seed/233/600",
                title = "Modal Usaha Nelayan",
                isVerified = true,
                minPinjaman = 5000000,
                returns = "10%",
                tenor = "12 Bulan",
                target = 300000000,
                currentPinjaman = 200000000
            ),
            PinjamanCardParcel(
                imageLink = "https://picsum.photos/seed/4704/600",
                title = "Modal Usaha Nelayan",
                isVerified = true,
                minPinjaman = 5000000,
                returns = "10%",
                tenor = "12 Bulan",
                target = 300000000,
                currentPinjaman = 200000000
            ),
            PinjamanCardParcel(
                imageLink = "https://picsum.photos/seed/4705/600",
                title = "Modal Usaha Nelayan",
                isVerified = true,
                minPinjaman = 5000000,
                returns = "10%",
                tenor = "12 Bulan",
                target = 300000000,
                currentPinjaman = 200000000
            ),
            PinjamanCardParcel(
                imageLink = "https://picsum.photos/seed/4706/600",
                title = "Modal Usaha Nelayan",
                isVerified = true,
                minPinjaman = 5000000,
                returns = "10%",
                tenor = "12 Bulan",
                target = 300000000,
                currentPinjaman = 200000000
            ),
        )
    }

    Scaffold(bottomBar = { BottomBarBorrower(navController) }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)

        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    HeaderBorrower()
                    ContainerCardBorrower()
                    BoxCardBorrower()
                    Text(
                        text = "Pendanaan Aktif",
                        color = primaryDark,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                datas.forEach { data ->
                    item {
                        Column(modifier = Modifier.padding(horizontal = 16.dp)) {


                            CardPinjamanBorrower(data = data, onClick = {
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