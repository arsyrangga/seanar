package com.rangga.seanar.ui.component.lender

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rangga.seanar.R
import com.rangga.seanar.helper.Utils

@Composable
fun ContainerCardLender(funding: String, donation: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CardHomeLender(
            modifier = Modifier,
            image = painterResource(id = R.drawable.fund1),
            title = "Total Pendanaan",
            description = Utils.formatCurrency(funding.toInt()),

            )
        CardHomeLender(
            modifier = Modifier,
            image = painterResource(id = R.drawable.fund2),
            title = "Total Donasi",
            description = Utils.formatCurrency(donation.toInt()),

            )
    }
}