package com.rangga.seanar.ui.component.lender

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.rangga.seanar.data.parcel.HomeCardParcel
import com.rangga.seanar.data.parcel.ListFundingParcel
import com.rangga.seanar.helper.Utils
import com.rangga.seanar.ui.theme.gray_200
import com.rangga.seanar.ui.theme.gray_500
import com.rangga.seanar.ui.theme.primary

@Composable
fun CardComponent(data: ListFundingParcel, onClick: () -> Unit = {}) {
    Box(modifier = Modifier
        .padding(bottom = 20.dp)
        .fillMaxWidth(0.48F)
        .background(gray_200, RoundedCornerShape(4))
        .clickable { onClick() }

    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(data.linkImage),
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
                    text = data.title.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(
                        text = data.organizationName.toString(),
                        color = gray_500,
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                    )

                    if (data.isFraud == false) {
                        Image(
                            painter = painterResource(id = R.drawable.trusted),
                            contentDescription = "trust"
                        )
                    }
                }


                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Min. Pinjaman",
                        color = gray_500,
                        fontSize = 12.sp,
                        lineHeight = 18.sp
                    )
                    Text(
                        text = "${data.minimumLoan}",
                        color = primary,
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(top = 8.dp, end = 8.dp)
                    ) {
                        Text(
                            text = "Return", color = gray_500, fontSize = 12.sp, lineHeight = 18.sp
                        )
                        Text(
                            text = "${data.jsonMemberReturn}%",
                            color = primary,
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(1F)
                            .padding(top = 8.dp)
                    ) {
                        Text(
                            text = "Tenor", color = gray_500, fontSize = 12.sp, lineHeight = 18.sp
                        )
                        Text(
                            text = "${data.duration} Bulan",
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
}