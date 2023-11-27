package com.rangga.seanar.ui.screen.lender

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rangga.seanar.R
import com.rangga.seanar.data.parcel.BottomSheetParcel
import com.rangga.seanar.ui.component.ButtonComponent
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailDonasiLenderScreen(navController: NavController) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    fun handleClick () {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                showBottomSheet = false
                navController.navigate(homeLenderScreen)
            }
        }
    }
    Scaffold(
        topBar = {
            TopBar(
                title = "Bantu Pendanaan",
                navController = navController
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            LenderHeaderDetail()
            DetailCardDonasi()
            DetailCardInputDonasi()
            ButtonComponent(
                onClick = {
                    showBottomSheet = true
                },
                text = "Bantu Donasi",
                modifier = Modifier.padding(16.dp),
                disabled = false
            )
            if (showBottomSheet) {
                BottomSheetComponent(
                    data = BottomSheetParcel(
                        title = "Donasi Berhasil",
                        nominal = "Rp500.000,00",
                        desc = "Ke Program Pelestarian Terumbu Karang di Kulon Progo"
                    ),
                    setShowBottomSheet = { handleClick() },
                    sheetState = sheetState,
                    onClick = {
                        handleClick()
                    }
                )
            }
        }
    }
}