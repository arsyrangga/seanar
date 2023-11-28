package com.rangga.seanar.ui.component.borrower

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rangga.seanar.R
import com.rangga.seanar.ui.component.ButtonComponent
import com.rangga.seanar.ui.component.lender.CardHomeLender
import com.rangga.seanar.ui.theme.primaryDark
import com.rangga.seanar.ui.theme.secondary

@Composable
fun ContainerCardBorrower(type: String = "pendanaan") {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-30).dp)
                .background(secondary, RoundedCornerShape(15.dp))
                .padding(top = 25.dp, bottom = 25.dp, start = 20.dp, end = 20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.fund1),
                    contentDescription = "FUND ICON",
                    modifier = Modifier.weight(0.5F), contentScale = ContentScale.FillWidth
                )
                Column(
                    Modifier
                        .weight(2F)
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = if (type == "pendanaan") {
                            "Saldo Pinjaman"
                        } else {
                            "Saldo Donasi"
                        },
                        color = primaryDark,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Rp20.000.000",
                        color = primaryDark,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                ButtonComponent(
                    onClick = { },
                    text = if (type == "pendanaan") {
                        "Buka Pendanaan"
                    } else {
                        "Buka Donasi"
                    },
                    modifier = Modifier.weight(2F),
                    fontSize = 13.sp
                )
            }
        }
    }
}