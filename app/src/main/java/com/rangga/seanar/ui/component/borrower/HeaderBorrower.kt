package com.rangga.seanar.ui.component.borrower

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.rangga.seanar.ui.theme.primary
import com.rangga.seanar.ui.theme.white


@Composable
fun HeaderBorrower(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .height(175.dp)
            .background(primary)
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.70F), verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Pendanaan",
                    color = white,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Kelompok Nelayan",
                    color = white,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(1F),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Row (modifier = Modifier.fillMaxWidth(0.8F)) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile Image Home",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth,
                    )
                }

            }

        }
    }
}