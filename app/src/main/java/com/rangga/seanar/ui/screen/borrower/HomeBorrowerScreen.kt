package com.rangga.seanar.ui.screen.borrower

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.rangga.seanar.data.parcel.DetailUserParcel
import com.rangga.seanar.data.parcel.DonasiCardBorrowerParcel
import com.rangga.seanar.data.parcel.HomeCardParcel
import com.rangga.seanar.data.parcel.ListFundingParcel
import com.rangga.seanar.data.parcel.PendanaanCardParcel
import com.rangga.seanar.data.parcel.PinjamanCardParcel
import com.rangga.seanar.data.retrofit.ApiRequest
import com.rangga.seanar.helper.TokenDatastore
import com.rangga.seanar.ui.component.BottomBar
import com.rangga.seanar.ui.component.BottomBarBorrower
import com.rangga.seanar.ui.component.LoadingComponent
import com.rangga.seanar.ui.component.borrower.BoxCardBorrower
import com.rangga.seanar.ui.component.borrower.CardDonasiBorrower
import com.rangga.seanar.ui.component.borrower.CardPinjamanBorrower
import com.rangga.seanar.ui.component.borrower.ContainerCardBorrower
import com.rangga.seanar.ui.component.borrower.HeaderBorrower
import com.rangga.seanar.ui.component.lender.CardPendanaan
import com.rangga.seanar.ui.navigation.detailPendanaanLenderScreen
import com.rangga.seanar.ui.theme.primaryDark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeBorrowerScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sessionManager = TokenDatastore(context = context)
    val userId = sessionManager.getUserId()
    var detailUser by remember {
        mutableStateOf(sessionManager.getDetail())
    }

    var fundingBalance by remember {
        mutableStateOf("0")
    }

    var loading by remember {
        mutableStateOf(false)
    }

    var listOpenFunding = remember {
        mutableStateListOf<PinjamanCardParcel>()
    }

    var listCloseFunding = remember {
        mutableStateListOf<PinjamanCardParcel>()
    }

    fun getUserDetail() {
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    loading = true
                    val response =
                        ApiRequest.getApiService(context).getUserDetailBorrower(userId.toString())
                            .awaitResponse()

                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        val username = data?.organizationName
                        val email = data?.email
                        val phone = data?.phoneNumber
                        val payload = DetailUserParcel(
                            phoneNumber = phone.toString(),
                            username = username.toString(),
                            email = email.toString()
                        )
                        sessionManager.saveDetailUser(
                            payload
                        )
                        detailUser = payload
                    }

                } catch (err: Throwable) {
                } finally {
                    loading = false
                }
            }
        }
    }

    fun getSaldo() {
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    loading = true
                    val response = ApiRequest.getApiService(context)
                        .getTotalFundingBalanceBorrower(userId.toString()).awaitResponse()

                    if (response.isSuccessful) {
                        fundingBalance = response.body()?.data?.fundingBalance.toString()
                    }

                } catch (err: Throwable) {
                } finally {
                    loading = false
                }
            }
        }
    }

    fun getOpenFundingList() {
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    loading = true
                    val response =
                        ApiRequest.getApiService(context).getOpenFundingBorrower(userId.toString())
                            .awaitResponse()

                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        Log.d("TEH", data.toString())
                        data?.let {
                            it?.map { list ->
                                listOpenFunding.add(
                                    PinjamanCardParcel(
                                        imageLink = list?.linkImage.toString(),
                                        title = list?.title.toString(),
                                        isVerified = true,
                                        minPinjaman = list?.minimumLoan.toString(),
                                        returns = "${list?.jsonMemberReturn}%",
                                        tenor = "${list?.duration} Bulan",
                                        target = list?.targetAmount.toString().toInt(),
                                        currentPinjaman = list?.currentAmount.toString().toInt()
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

    fun getCloseFundingList() {
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    loading = true
                    val response =
                        ApiRequest.getApiService(context).getCloseFundingBorrower(userId.toString())
                            .awaitResponse()

                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        Log.d("TEH", data.toString())
                        data?.let {
                            it?.map { list ->
                                listCloseFunding.add(
                                    PinjamanCardParcel(
                                        imageLink = list?.linkImage.toString(),
                                        title = list?.title.toString(),
                                        isVerified = true,
                                        minPinjaman = list?.minimumLoan.toString(),
                                        returns = "${list?.jsonMemberReturn}%",
                                        tenor = "${list?.duration} Bulan",
                                        target = list?.targetAmount.toString().toInt(),
                                        currentPinjaman = list?.currentAmount.toString().toInt()
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
        getUserDetail()
        getSaldo()
        getOpenFundingList()
        getCloseFundingList()
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
                    ContainerCardBorrower(balance = fundingBalance)
                    BoxCardBorrower(data1 = "0", data2 = fundingBalance, data3 = "100%")
                    Text(
                        text = "Pendanaan Aktif",
                        color = primaryDark,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                listOpenFunding.forEach { data ->
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

                if (listCloseFunding.isNotEmpty()) {
                    item {
                        Text(
                            text = "Pendanaan Tidak Aktif",
                            color = primaryDark,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }


                listCloseFunding.forEach { data ->
                    item {
                        Row(modifier = Modifier.padding(16.dp)) {
                            CardPinjamanBorrower(data = data, onClick = {})
                        }
                    }
                }


            }
        }
    }
}