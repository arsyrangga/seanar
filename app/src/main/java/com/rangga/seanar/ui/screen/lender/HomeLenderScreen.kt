package com.rangga.seanar.ui.screen.lender

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rangga.seanar.data.parcel.HomeCardParcel
import com.rangga.seanar.data.parcel.LoginParcel
import com.rangga.seanar.data.retrofit.ApiRequest
import com.rangga.seanar.helper.TokenDatastore
import com.rangga.seanar.helper.Utils
import com.rangga.seanar.ui.component.BottomBar
import com.rangga.seanar.ui.component.LoadingComponent
import com.rangga.seanar.ui.component.lender.BoxCardHome
import com.rangga.seanar.ui.component.lender.CardComponent
import com.rangga.seanar.ui.component.lender.ContainerCardLender
import com.rangga.seanar.ui.component.lender.HeaderLender
import com.rangga.seanar.ui.navigation.detailPendanaanLenderScreen
import com.rangga.seanar.ui.theme.primaryDark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeLenderScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sessionManager = TokenDatastore(context = context)
    val userId = sessionManager.getUserId()

    var loading by remember {
        mutableStateOf(false)
    }
    var totalFunding by remember {
        mutableStateOf("0")
    }

    var totalDonation by remember {
        mutableStateOf("0")
    }


    val datas = remember {
        mutableStateListOf(
            HomeCardParcel(
                imageLink = "https://picsum.photos/seed/430/600",
                title = "Modal Usaha Nelayan",
                description = "Kelompok Nelayan Mulyo",
                isVerified = true,
                minPinjaman = 5000000,
                returns = "10%",
                tenor = "12 Bulan"
            ), HomeCardParcel(
                imageLink = "https://picsum.photos/seed/420/600",
                title = "Modal Usaha Nelayan",
                description = "Kelompok Nelayan Mulyo",
                isVerified = true,
                minPinjaman = 5000000,
                returns = "10%",
                tenor = "12 Bulan"
            ), HomeCardParcel(
                imageLink = "https://picsum.photos/seed/400/600",
                title = "Modal Usaha Nelayan",
                description = "Kelompok Nelayan Mulyo",
                isVerified = true,
                minPinjaman = 5000000,
                returns = "10%",
                tenor = "12 Bulan"
            ), HomeCardParcel(
                imageLink = "https://picsum.photos/seed/450/600",
                title = "Modal Usaha Nelayan",
                description = "Kelompok Nelayan Mulyo",
                isVerified = true,
                minPinjaman = 5000000,
                returns = "10%",
                tenor = "12 Bulan"
            )
        )
    }

    fun getTotalFunding() {
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    loading = true
                    val response =
                        ApiRequest.getApiService(context).getTotalFunding(userId.toString())
                            .awaitResponse()

                    if (response.isSuccessful) {
                        Log.d("tej", response.body()?.data?.totalFunding.toString())
                        totalFunding = response.body()?.data?.totalFunding.toString()
                    }

                } catch (err: Throwable) {
                } finally {
                    loading = false
                }
            }
        }
    }

    fun getTotalDonation() {
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    loading = true
                    val response =
                        ApiRequest.getApiService(context).getTotalDonation(userId.toString())
                            .awaitResponse()

                    if (response.isSuccessful) {
                        totalDonation = response.body()?.data?.totalDonation.toString()
                    }

                } catch (err: Throwable) {
                } finally {
                    loading = false
                }
            }
        }
    }

    LaunchedEffect(key1 = 1) {
        getTotalFunding()
        getTotalDonation()
    }


    val total = Utils.formatCurrency(
        totalFunding.toInt() + totalDonation
            .toInt()
    )

    Scaffold(bottomBar = { BottomBar(navController) }) { innerPadding ->
        LoadingComponent(loading)
        Column(
            modifier = Modifier.padding(innerPadding)

        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    HeaderLender()
                    ContainerCardLender(donation = totalDonation, funding = totalFunding)
                    BoxCardHome(pencairan = total, angsuran = total)
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
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        datas?.forEach {
                            CardComponent(data = it,
                                onClick = { navController.navigate(detailPendanaanLenderScreen) })
                        }
                    }

                }
            }
        }
    }
}

