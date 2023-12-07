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
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rangga.seanar.data.parcel.DonasiCardParcel
import com.rangga.seanar.data.parcel.ListFundingParcel
import com.rangga.seanar.data.parcel.PendanaanCardParcel
import com.rangga.seanar.data.response.listDonasi.DonasiItem
import com.rangga.seanar.data.response.listDonasi.ListDonasiResponse
import com.rangga.seanar.data.retrofit.ApiRequest
import com.rangga.seanar.helper.TokenDatastore
import com.rangga.seanar.ui.component.BottomBar
import com.rangga.seanar.ui.component.LoadingComponent
import com.rangga.seanar.ui.component.TopBar
import com.rangga.seanar.ui.component.lender.CardDonasi
import com.rangga.seanar.ui.component.lender.CardPendanaan
import com.rangga.seanar.ui.navigation.detailDonasiLenderScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DonasiLenderScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sessionManager = TokenDatastore(context = context)

    var listDonasi = remember {
        mutableStateListOf<DonasiCardParcel>()
    }

    var loading by remember {
        mutableStateOf(false)
    }

    fun getFundingList() {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    loading = true
                    val response =
                        ApiRequest.getApiService(context).getListDonation().awaitResponse()

                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        Log.d("TEH", data.toString())
                        data.let {
                            it?.map { list ->
                                listDonasi.add(
                                    DonasiCardParcel(
                                        imageLink = list?.linkImage.toString(),
                                        title = list?.title.toString(),
                                        description = list?.organizationName.toString(),
                                        isVerified = true,
                                        terkumpul = list?.currentAmount!!.toInt(),
                                        target = list?.targetAmount!!.toInt()
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
        getFundingList()
    }

    Scaffold(bottomBar = { BottomBar(navController) },
        topBar = { TopBar(title = "Donasi", navController = navController) }) { innerPadding ->
        LoadingComponent(isLoading = loading)
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
                        listDonasi?.forEach {
                            CardDonasi(data = it, onClick = {
                                navController.navigate(
                                    detailDonasiLenderScreen
                                )
                            })
                        }
                    }

                }
            }
        }
    }
}