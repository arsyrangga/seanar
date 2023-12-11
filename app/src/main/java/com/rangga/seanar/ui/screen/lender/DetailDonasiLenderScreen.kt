package com.rangga.seanar.ui.screen.lender

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Query
import com.rangga.seanar.R
import com.rangga.seanar.data.parcel.BottomSheetParcel
import com.rangga.seanar.data.parcel.DataTxParcel
import com.rangga.seanar.data.response.DonationData
import com.rangga.seanar.data.response.FundingData
import com.rangga.seanar.data.retrofit.ApiRequest
import com.rangga.seanar.helper.TokenDatastore
import com.rangga.seanar.helper.Utils
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailDonasiLenderScreen(navController: NavController, query: String) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sessionManager = TokenDatastore(context = context)
    val lender_id = sessionManager.getUserId()


    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    var dataDetail by remember {
        mutableStateOf(DonationData())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    var nominal by remember {
        mutableStateOf("")
    }


    fun changeNominal(data: String) {
        nominal = data
    }

    fun handleClick() {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                showBottomSheet = false
                navController.navigate(homeLenderScreen)
            }
        }
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
                        type = "DONATION",
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
        getDonationDetail()
    }


    Scaffold(topBar = {
        TopBar(
            title = "Donasi", navController = navController
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
            DetailCardInputDonasi(nominal = nominal, changeNominal = { changeNominal(it) })
            ButtonComponent(
                onClick = {
                    PostTransaction()
                }, text = "Bantu Donasi", modifier = Modifier.padding(16.dp), disabled = nominal.isEmpty()
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