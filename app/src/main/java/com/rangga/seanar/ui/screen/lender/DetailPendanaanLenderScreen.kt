package com.rangga.seanar.ui.screen.lender

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Query
import coil.compose.rememberAsyncImagePainter
import com.rangga.seanar.data.parcel.BottomSheetParcel
import com.rangga.seanar.data.parcel.DataTxParcel
import com.rangga.seanar.data.parcel.ListFundingParcel
import com.rangga.seanar.data.response.FundingData
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
fun DetailPendanaanLenderScreen(navController: NavController, query: String) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sessionManager = TokenDatastore(context = context)
    val lender_id = sessionManager.getUserId()

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    var dataDetail by remember {
        mutableStateOf(FundingData())
    }

    var nominal by remember {
        mutableStateOf("")
    }

    var loading by remember {
        mutableStateOf(false)
    }

    fun changeNominal(data: String) {
        nominal = data.replace(Regex("[^0-9]"), "")
    }

    fun handleClick() {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                showBottomSheet = false
                navController.navigate(homeLenderScreen)
            }
        }
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

    fun PostTransaction() {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    loading = true
                    val payload = DataTxParcel(
                        lenderId = lender_id.toString(),
                        srcAccNumber = "123",
                        dstAccNumber = "456",
                        postId = query.toInt(),
                        nominal = nominal?.toInt(),
                        type = "FUNDING",
                        txnStatus = "SUCCESS"
                    )
                    val response =
                        ApiRequest.getApiService(context).postTransaction(payload).awaitResponse()
                    if (response.isSuccessful) {
                        showBottomSheet = true
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
        getFundingDetail()
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
            DetailCardInputPendanaan(data = dataDetail, nominal = nominal, changeNominal = { changeNominal(it) })
            ButtonComponent(
                onClick = {
                    PostTransaction()
                },
                text = "Bantu Pendanaan",
                modifier = Modifier.padding(16.dp),
                disabled = nominal.isEmpty()
            )
            if (showBottomSheet) {
                BottomSheetComponent(data = BottomSheetParcel(
                    title = dataDetail.title.toString(),
                    nominal = Utils.formatCurrency(nominal.toInt()),
                    desc = dataDetail.organizationName.toString()
                ), setShowBottomSheet = { handleClick() }, sheetState = sheetState, onClick = {
                    handleClick()
                })
            }
        }
    }
}



