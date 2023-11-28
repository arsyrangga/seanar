package com.rangga.seanar.ui.component.borrower

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.rangga.seanar.R
import com.rangga.seanar.data.parcel.DonasiCardBorrowerParcel
import com.rangga.seanar.data.parcel.DonasiCardParcel
import com.rangga.seanar.helper.Utils
import com.rangga.seanar.ui.theme.gray_200
import com.rangga.seanar.ui.theme.gray_500
import com.rangga.seanar.ui.theme.primary
import com.rangga.seanar.ui.theme.secondary

@Composable
fun CardDonasiBorrower(data: DonasiCardBorrowerParcel, onClick: () -> Unit = {}) {
    val painter = rememberAsyncImagePainter(data.imageLink)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(gray_200, RoundedCornerShape(4))
            .clickable { onClick() }
    ) {
        Row() {

            Image(
                painter = painter,
                contentDescription = "Card Image",
                modifier = Modifier
                    .size(135.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.FillHeight,
                alignment = Alignment.Center
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(1F)
                    .padding(20.dp)

            ) {

                Text(
                    text = data.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 16.dp)
                ) {
                    Text(
                        text = "Terkumpul", color = gray_500, fontSize = 12.sp, lineHeight = 18.sp
                    )
                    Text(
                        text = Utils.formatCurrency(data.terkumpul),
                        color = primary,
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                LinearProgressIndicator(
                    progress = (data.terkumpul.toDouble() / data.target).toFloat(),
                    modifier = Modifier.fillMaxWidth(),
                    color = primary,
                    trackColor = secondary
                )

                Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Lihat Detail", fontSize = 12.sp)
                    Image(painter = painterResource(id = R.drawable.arrow_right), contentDescription = "Image Right", modifier = Modifier.height(20.dp), contentScale = ContentScale.FillHeight)
                }

            }

        }
    }
}