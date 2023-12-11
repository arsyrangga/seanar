package com.rangga.seanar.ui.screen.lender

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Query
import coil.compose.rememberAsyncImagePainter
import com.rangga.seanar.data.parcel.BottomSheetParcel
import com.rangga.seanar.data.parcel.ListFundingParcel
import com.rangga.seanar.data.response.FundingData
import com.rangga.seanar.data.response.ListTx
import com.rangga.seanar.data.response.ListTxResponse
import com.rangga.seanar.data.retrofit.ApiRequest
import com.rangga.seanar.helper.TokenDatastore
import com.rangga.seanar.helper.Utils
import com.rangga.seanar.ui.component.BottomBar
import com.rangga.seanar.ui.component.ButtonComponent
import com.rangga.seanar.ui.component.LoadingComponent
import com.rangga.seanar.ui.component.TopBar
import com.rangga.seanar.ui.component.lender.BottomSheetComponent
import com.rangga.seanar.ui.component.lender.DetailCardInputPendanaan
import com.rangga.seanar.ui.component.lender.DetailCardPendanaan
import com.rangga.seanar.ui.component.lender.LenderHeaderDetail
import com.rangga.seanar.ui.navigation.homeLenderScreen
import com.rangga.seanar.ui.theme.primary
import com.rangga.seanar.ui.theme.secondary
import com.rangga.seanar.ui.theme.white
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailPendanaanBorrowerScreen(navController: NavController, query: String) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sessionManager = TokenDatastore(context = context)


    var dataDetail by remember {
        mutableStateOf(FundingData())
    }

    var listTx = remember {
        mutableStateListOf<ListTx>()
    }

    var loading by remember {
        mutableStateOf(false)
    }

    fun getFundingDetail() {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    loading = true
                    val response =
                        ApiRequest.getApiService(context).getFundingDetail(query).awaitResponse()

                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        Log.d("TEH", data.toString())
                        dataDetail = data!!
                    }

                } catch (err: Throwable) {
                    Log.d("TEH", err.toString())

                } finally {
                    loading = false
                }
            }
        }
    }

    fun getTxList() {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                try {

                    loading = true
                    val response =
                        ApiRequest.getApiService(context).getTxList(query).awaitResponse()

                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        data?.forEach() {
                            listTx.add(it!!)
                        }
                    }

                } catch (err: Throwable) {
                    Log.d("TEHX", err.toString())
                } finally {
                    loading = false
                }
            }
        }
    }

    LaunchedEffect(key1 = 1) {
        getFundingDetail()
        getTxList()

    }

    Scaffold(topBar = {
        TopBar(
            title = "Bantu Pendanaan", navController = navController
        )
    }) { innerPadding ->
        LoadingComponent(isLoading = loading)
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            LenderHeaderDetail(dataDetail.linkImage.toString())
            DetailCardPendanaan(dataDetail)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                    .offset(y = (-50).dp)
                    .shadow(
                        elevation = 10.dp, spotColor = secondary, shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .background(white, RoundedCornerShape(15.dp))
                    .padding(20.dp)
            ) {
                Text(text = "Riwayat Pendanaan", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                listTx?.map {
                    ListData(name = it.username.toString(), nominal = it.nominal.toString() )
                }
            }
        }
    }
}

@Composable
fun ListData(name: String, nominal: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Text(text = "Dari ", fontSize = 12.sp)
            Text(text = name, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }

        Text(
            text = "+ ${Utils.formatCurrency(nominal.replace(Regex(".{3}$"), "").toInt())}",
            color = primary,
            fontSize = 12.sp
        )

    }
}


