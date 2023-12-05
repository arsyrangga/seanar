package com.rangga.seanar.ui.component.lender

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rangga.seanar.ui.component.TextBetween
import com.rangga.seanar.ui.theme.primary
import com.rangga.seanar.ui.theme.primaryDark

@Composable
fun BoxCardHome(angsuran : String, pencairan : String, paymentRate : String = "100%"){
    Box(modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth().border(width = 1.dp, color = primary, shape = RoundedCornerShape(8.dp))) {
        Column(
            Modifier.padding(20.dp)
        ) {
            Text(text = "Pendanaan Mitra Aktif", color = primaryDark, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 8.dp))
            TextBetween(title = "Angsuran Terbayar", description = angsuran)
            TextBetween(title = "Menunggu Pencairan", description = pencairan)
            TextBetween(title = "Pembayaran Lancar", description = paymentRate)
        }
    }
}