package com.rangga.seanar.ui.component.borrower

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
fun BoxCardBorrower(type: String = "pendanaan") {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .border(width = 1.dp, color = primary, shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            Modifier.padding(20.dp)
        ) {
            Text(
                text = if (type == "pendanaan") {
                    "Pinjaman Aktif"
                } else {
                    "Pendapatan Donasi"
                },
                color = primaryDark,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TextBetween(
                title = if (type == "pendanaan") {
                    "Pinjaman Belum Terbayar"
                } else {
                    "Total Donasi yang Didapatkan"
                }, description = "Rp20.000.000"
            )
            TextBetween(
                title = if (type == "pendanaan") {
                    "Pinjaman Terbayar"
                } else {
                    "Donasi yang Sudah Dicairkan"
                }, description = "Rp10.000.000"
            )
            TextBetween(
                title = if (type == "pendanaan") {
                    "Presentase Pembayaran Cicilan"
                } else {
                    "Donasi yang Belum Dicairkan"
                }, description = "97,2%"
            )
        }
    }
}