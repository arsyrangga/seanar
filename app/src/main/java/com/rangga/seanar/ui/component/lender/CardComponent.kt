package com.rangga.seanar.ui.component.lender

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.rangga.seanar.R
import com.rangga.seanar.data.parcel.HomeCardParcel
import com.rangga.seanar.ui.theme.gray_200
import com.rangga.seanar.ui.theme.gray_500
import com.rangga.seanar.ui.theme.primary

@Composable
fun CardComponent(data: HomeCardParcel) {
    Box(
        modifier = Modifier
            .padding( bottom = 20.dp)
            .fillMaxWidth(0.48F)
            .background(gray_200, RoundedCornerShape(4))
    )
    {
        Column {
            Image(
                painter = rememberAsyncImagePainter(data.imageLink),
                contentDescription = "Card Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)

            ) {
                Text(
                    text = data.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = data.description,
                    color = gray_500,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Min. Pinjaman",
                        color = gray_500,
                        fontSize = 12.sp,
                        lineHeight = 18.sp
                    )
                    Text(
                        text = data.minPinjaman,
                        color = primary,
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(
                        text = "Return",
                        color = gray_500,
                        fontSize = 12.sp,
                        lineHeight = 18.sp
                    )
                    Text(
                        text = data.returns,
                        color = primary,
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(
                        text = "Tenor",
                        color = gray_500,
                        fontSize = 12.sp,
                        lineHeight = 18.sp
                    )
                    Text(
                        text = data.tenor,
                        color = primary,
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }

        }
    }
}