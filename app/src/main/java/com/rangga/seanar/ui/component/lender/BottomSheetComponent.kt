package com.rangga.seanar.ui.component.lender

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rangga.seanar.R
import com.rangga.seanar.data.parcel.BottomSheetParcel
import com.rangga.seanar.ui.component.ButtonComponent
import com.rangga.seanar.ui.theme.black
import com.rangga.seanar.ui.theme.gray_500
import com.rangga.seanar.ui.theme.primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetComponent (data : BottomSheetParcel,setShowBottomSheet: () -> Unit, sheetState: SheetState, onClick: () ->Unit){
    ModalBottomSheet(
        onDismissRequest = {
            setShowBottomSheet()
        },
        sheetState = sheetState
    ) {
        // Sheet content
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.icon_success), contentDescription = "icon success", modifier = Modifier
                .padding(bottom = 16.dp)
                .size(100.dp))
            Text(text = data.title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = primary, modifier = Modifier.padding(bottom = 16.dp))
            Text(text = data.nominal, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = black, modifier = Modifier.padding(bottom = 8.dp))
            Text(text = data.desc, fontSize = 12.sp, color = gray_500, modifier = Modifier.padding(bottom = 8.dp))
            ButtonComponent(onClick = { onClick() }, text = "Kembali Ke Beranda", modifier = Modifier.padding(vertical = 8.dp))
        }


    }
}