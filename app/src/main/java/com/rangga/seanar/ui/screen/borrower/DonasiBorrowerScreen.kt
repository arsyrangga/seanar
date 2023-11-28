package com.rangga.seanar.ui.screen.borrower

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rangga.seanar.data.parcel.DonasiCardBorrowerParcel
import com.rangga.seanar.data.parcel.DonasiCardParcel
import com.rangga.seanar.data.parcel.HomeCardParcel
import com.rangga.seanar.ui.component.BottomBarBorrower
import com.rangga.seanar.ui.component.borrower.BoxCardBorrower
import com.rangga.seanar.ui.component.borrower.CardDonasiBorrower
import com.rangga.seanar.ui.component.borrower.ContainerCardBorrower
import com.rangga.seanar.ui.component.borrower.HeaderBorrower

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DonasiBorrowerScreen(navController: NavController) {

    val datas = remember {
        mutableStateListOf(
            DonasiCardBorrowerParcel(
                imageLink = "https://picsum.photos/seed/479/600",
                title = "Modal Usaha Nelayan",
                isVerified = true,
                terkumpul = 700000000,
                target = 1000000000
            ),
            DonasiCardBorrowerParcel(
                imageLink = "https://picsum.photos/seed/120/600",
                title = "Modal Usaha Nelayan",
                isVerified = true,
                terkumpul = 200000000,
                target = 1000000000

            ),
            DonasiCardBorrowerParcel(
                imageLink = "https://picsum.photos/seed/409/600",
                title = "Modal Usaha Nelayan",
                isVerified = true,
                terkumpul = 500000000,
                target = 1000000000

            ), DonasiCardBorrowerParcel(
                imageLink = "https://picsum.photos/seed/452/600",
                title = "Modal Usaha Nelayan",
                isVerified = true,
                terkumpul = 400000000,
                target = 1000000000
            )
        )
    }

    Scaffold(bottomBar = { BottomBarBorrower(navController) }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)

        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    HeaderBorrower()
                    ContainerCardBorrower(type = "donasi")
                    BoxCardBorrower(type = "donasi")
                }

                datas.forEach { data ->
                    item {
                        Row(modifier = Modifier.padding(16.dp)) {
                            CardDonasiBorrower(data = data, onClick = {})
                        }
                    }
                }

            }

        }
    }
}