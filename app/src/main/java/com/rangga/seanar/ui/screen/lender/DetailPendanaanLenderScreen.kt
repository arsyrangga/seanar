package com.rangga.seanar.ui.screen.lender

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.rangga.seanar.data.parcel.BottomSheetParcel
import com.rangga.seanar.ui.component.BottomBar
import com.rangga.seanar.ui.component.ButtonComponent
import com.rangga.seanar.ui.component.TopBar
import com.rangga.seanar.ui.component.lender.BottomSheetComponent
import com.rangga.seanar.ui.component.lender.DetailCardInputPendanaan
import com.rangga.seanar.ui.component.lender.DetailCardPendanaan
import com.rangga.seanar.ui.component.lender.LenderHeaderDetail
import com.rangga.seanar.ui.navigation.homeLenderScreen
import com.rangga.seanar.ui.theme.primary
import com.rangga.seanar.ui.theme.secondary
import com.rangga.seanar.ui.theme.white
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailPendanaanLenderScreen(navController: NavController) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    fun handleClick() {
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
            DetailCardPendanaan()
            DetailCardInputPendanaan()
            ButtonComponent(
                onClick = { showBottomSheet = true },
                text = "Bantu Pendanaan",
                modifier = Modifier.padding(16.dp),
                disabled = false
            )
            if (showBottomSheet) {
                BottomSheetComponent(
                    data = BottomSheetParcel(
                        title = "Pendanaan Berhasil",
                        nominal = "Rp5.000.000,00",
                        desc = "Ke Kelompok Nelayan Mulyo"
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