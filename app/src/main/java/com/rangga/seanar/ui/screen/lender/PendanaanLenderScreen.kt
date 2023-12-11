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
import com.rangga.seanar.data.parcel.ListFundingParcel
import com.rangga.seanar.data.parcel.PendanaanCardParcel
import com.rangga.seanar.data.retrofit.ApiRequest
import com.rangga.seanar.helper.TokenDatastore
import com.rangga.seanar.helper.Utils
import com.rangga.seanar.ui.component.BottomBar
import com.rangga.seanar.ui.component.LoadingComponent
import com.rangga.seanar.ui.component.TopBar
import com.rangga.seanar.ui.component.lender.BoxCardHome
import com.rangga.seanar.ui.component.lender.CardComponent
import com.rangga.seanar.ui.component.lender.CardPendanaan
import com.rangga.seanar.ui.component.lender.ContainerCardLender
import com.rangga.seanar.ui.component.lender.HeaderLender
import com.rangga.seanar.ui.navigation.detailPendanaanLenderScreen
import com.rangga.seanar.ui.theme.primaryDark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PendanaanLenderScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sessionManager = TokenDatastore(context = context)

    var listFunding = remember {
        mutableStateListOf<ListFundingParcel>()
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
                        ApiRequest.getApiService(context).getListFunding().awaitResponse()

                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        Log.d("TEH", data.toString())
                        data.let {
                            it?.map { list ->
                                listFunding.add(
                                    ListFundingParcel(
                                        title = list?.title.toString(),
                                        description = list?.description.toString(),
                                        minimumLoan = list?.minimumLoan.toString(),
                                        jsonMemberReturn = list?.jsonMemberReturn,
                                        duration = list?.duration,
                                        linkImage = list?.linkImage,
                                        targetAmount = list?.targetAmount,
                                        postId = list?.postId,
                                        organizationName = list?.organizationName.toString()
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


    Scaffold(
        bottomBar = { BottomBar(navController) },
        topBar = { TopBar(title = "Mitra Pendanaan", navController = navController) },
    ) { innerPadding ->
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
                        listFunding?.forEach {
                            CardPendanaan(data = PendanaanCardParcel(
                                imageLink = it?.linkImage.toString(),
                                title = it?.title.toString(),
                                description = it?.organizationName.toString(),
                                isVerified = true,
                                minPinjaman = Utils.formatCurrency(
                                    it?.minimumLoan?.replace(
                                        Regex(".{3}\$"),
                                        ""
                                    )!!.toInt()
                                ),
                                returns = it?.jsonMemberReturn.toString(),
                                tenor = it.duration.toString(),
                                target = it.targetAmount.toString()
                            ), onClick = {
                                navController.navigate(
                                    "${detailPendanaanLenderScreen}/${it.postId}"
                                )
                            })
                        }
                    }

                }
            }
        }
    }
}