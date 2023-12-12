package com.rangga.seanar.ui.screen.lender

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Query
import com.rangga.seanar.R
import com.rangga.seanar.data.parcel.BottomSheetParcel
import com.rangga.seanar.data.response.DonationData
import com.rangga.seanar.data.response.FundingData
import com.rangga.seanar.data.response.ListTx
import com.rangga.seanar.data.retrofit.ApiRequest
import com.rangga.seanar.helper.TokenDatastore
import com.rangga.seanar.ui.component.ButtonComponent
import com.rangga.seanar.ui.component.LoadingComponent
import com.rangga.seanar.ui.component.TopBar
import com.rangga.seanar.ui.component.lender.BottomSheetComponent
import com.rangga.seanar.ui.component.lender.DetailCardDonasi
import com.rangga.seanar.ui.component.lender.DetailCardInputDonasi
import com.rangga.seanar.ui.component.lender.DetailCardInputPendanaan
import com.rangga.seanar.ui.component.lender.DetailCardPendanaan
import com.rangga.seanar.ui.component.lender.LenderHeaderDetail
import com.rangga.seanar.ui.navigation.homeLenderScreen
import com.rangga.seanar.ui.theme.black
import com.rangga.seanar.ui.theme.gray_400
import com.rangga.seanar.ui.theme.gray_500
import com.rangga.seanar.ui.theme.primary
import com.rangga.seanar.ui.theme.secondary
import com.rangga.seanar.ui.theme.white
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailDonasiBorrowerScreen(navController: NavController, query: String) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sessionManager = TokenDatastore(context = context)

    var dataDetail by remember {
        mutableStateOf(DonationData())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    var listTx = remember {
        mutableStateListOf<ListTx>()
    }

    fun getDonationDetail() {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    loading = true
                    val response =
                        ApiRequest.getApiService(context).getDonationDetail(query).awaitResponse()

                    if (response.isSuccessful) {
                        val data = response.body()?.data
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
        getDonationDetail()
        getTxList()
    }


    Scaffold(topBar = {
        TopBar(
            title = "Bantu Pendanaan", navController = navController, role = "BORROWER"
        )
    }) { innerPadding ->
        LoadingComponent(isLoading = loading)
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            LenderHeaderDetail(dataDetail.linkImage.toString())
            DetailCardDonasi(dataDetail)
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