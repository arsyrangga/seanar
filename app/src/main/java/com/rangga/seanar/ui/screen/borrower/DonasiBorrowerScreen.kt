package com.rangga.seanar.ui.screen.borrower

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rangga.seanar.data.parcel.DonasiCardBorrowerParcel
import com.rangga.seanar.data.parcel.DonasiCardParcel
import com.rangga.seanar.data.parcel.HomeCardParcel
import com.rangga.seanar.data.parcel.PinjamanCardParcel
import com.rangga.seanar.data.retrofit.ApiRequest
import com.rangga.seanar.helper.TokenDatastore
import com.rangga.seanar.ui.component.BottomBarBorrower
import com.rangga.seanar.ui.component.LoadingComponent
import com.rangga.seanar.ui.component.borrower.BoxCardBorrower
import com.rangga.seanar.ui.component.borrower.CardDonasiBorrower
import com.rangga.seanar.ui.component.borrower.ContainerCardBorrower
import com.rangga.seanar.ui.component.borrower.HeaderBorrower
import com.rangga.seanar.ui.navigation.createDonasiBorrowerScreen
import com.rangga.seanar.ui.navigation.detailDonasiBorrowerScreen
import com.rangga.seanar.ui.theme.primaryDark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DonasiBorrowerScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sessionManager = TokenDatastore(context = context)
    val userId = sessionManager.getUserId()
    var detailUser by remember {
        mutableStateOf(sessionManager.getDetail())
    }

    var donasiBalance by remember {
        mutableStateOf("0")
    }

    var loading by remember {
        mutableStateOf(false)
    }

    var listOpenDonasi = remember {
        mutableStateListOf<DonasiCardBorrowerParcel>()
    }

    var listCloseDonasi = remember {
        mutableStateListOf<DonasiCardBorrowerParcel>()
    }

    fun getSaldo() {
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    loading = true
                    val response = ApiRequest.getApiService(context)
                        .getTotalDonationBalanceBorrower(userId.toString()).awaitResponse()

                    if (response.isSuccessful) {
                        donasiBalance = response.body()?.data?.donationBalance.toString()
                    }

                } catch (err: Throwable) {
                } finally {
                    loading = false
                }
            }
        }
    }

    fun getOpenDonasiList() {
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    loading = true
                    val response =
                        ApiRequest.getApiService(context).getOpenDonationBorrower(userId.toString())
                            .awaitResponse()

                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        Log.d("TEH", data.toString())
                        data?.let {
                            it?.map { list ->
                                listOpenDonasi.add(
                                    DonasiCardBorrowerParcel(
                                        imageLink = list?.linkImage.toString(),
                                        title = list?.title.toString(),
                                        isVerified = true,
                                        terkumpul = list?.currentAmount.toString().toInt(),
                                        target = list?.targetAmount.toString().toInt(),
                                        postId = list?.postId.toString()
                                    )
                                )
                            }
                        }
                    }

                } catch (err: Throwable) {
                    Log.d("TEH", err.toString())

                } finally {
                    loading = false
                }
            }
        }
    }


    fun getCloseDonasiList() {
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    loading = true
                    val response = ApiRequest.getApiService(context)
                        .getCloseDonationBorrower(userId.toString()).awaitResponse()

                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        Log.d("TEH", data.toString())
                        data?.let {
                            it?.map { list ->
                                listCloseDonasi.add(
                                    DonasiCardBorrowerParcel(
                                        imageLink = list?.linkImage.toString(),
                                        title = list?.title.toString(),
                                        isVerified = true,
                                        terkumpul = list?.currentAmount.toString().toInt(),
                                        target = list?.targetAmount.toString().toInt(),
                                        postId = list?.postId.toString()

                                    )
                                )
                            }
                        }
                    }

                } catch (err: Throwable) {
                    Log.d("TEH", err.toString())

                } finally {
                    loading = false
                }
            }
        }
    }

    LaunchedEffect(key1 = 1) {
        getSaldo()
        getOpenDonasiList()
        getCloseDonasiList()
    }


    Scaffold(bottomBar = { BottomBarBorrower(navController) }) { innerPadding ->
        LoadingComponent(isLoading = loading)
        Column(
            modifier = Modifier.padding(innerPadding)

        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    HeaderBorrower(title = detailUser?.username.toString())
                    ContainerCardBorrower(type = "donasi", balance = donasiBalance, onClick = {
                        navController.navigate(
                            createDonasiBorrowerScreen
                        )
                    })
                    BoxCardBorrower(
                        type = "donasi", data1 = donasiBalance, data2 = "0", data3 = donasiBalance
                    )
                    Text(
                        text = "Donasi Aktif",
                        color = primaryDark,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                listOpenDonasi.forEach { data ->
                    item {
                        Row(modifier = Modifier.padding(16.dp)) {
                            CardDonasiBorrower(data = data,
                                onClick = { navController.navigate("$detailDonasiBorrowerScreen/${data?.postId}")})
                        }
                    }
                }

                if (listCloseDonasi.isNotEmpty()) {
                    item {
                        Text(
                            text = "Donasi Tidak Aktif",
                            color = primaryDark,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }


                listCloseDonasi.forEach { data ->
                    item {
                        Row(modifier = Modifier.padding(16.dp)) {
                            CardDonasiBorrower(data = data, onClick = { navController.navigate("$detailDonasiBorrowerScreen/${data?.postId}")})
                        }
                    }
                }

            }

        }
    }
}