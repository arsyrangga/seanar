package com.rangga.seanar.ui.component.lender

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.rangga.seanar.data.parcel.PendanaanCardParcel
import com.rangga.seanar.helper.Utils
import com.rangga.seanar.ui.theme.gray_200
import com.rangga.seanar.ui.theme.gray_500
import com.rangga.seanar.ui.theme.primary
import com.rangga.seanar.ui.theme.secondary
import com.rangga.seanar.ui.theme.warning_bold

@Composable
fun CardPendanaan(data: PendanaanCardParcel, onClick: ()->Unit = {}) {
    val painter = rememberAsyncImagePainter(data.imageLink)
    Box(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth()
            .background(gray_200, RoundedCornerShape(4))
            .clickable { onClick() }
    ) {
        Row() {

            Image(
                painter = painter,
                contentDescription = "Card Image",
                modifier = Modifier
                    .size(170.dp)
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

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(
                        text = data.description.toString(),
                        color = gray_500,
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                    )

                    if (!data.isFraud) {
                        Image(
                            painter = painterResource(id = R.drawable.trusted),
                            contentDescription = "trust"
                        )
                    }
                }

                Text(
                    text = data.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
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

                Row {
                    Column(
                        modifier = Modifier
                            .padding(top = 8.dp, end = 8.dp)
                    ) {
                        Text(
                            text = "Return", color = gray_500, fontSize = 12.sp, lineHeight = 18.sp
                        )
                        Text(
                            text = "${data.returns}%",
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
                            text = "${data.tenor} Bulan",
                            color = primary,
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(
                        text = "Target", color = gray_500, fontSize = 12.sp, lineHeight = 18.sp
                    )
                    Text(
                        text = "${Utils.formatCurrency(data.target.toInt())}",
                        color = warning_bold,
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 8.dp)
                    )
                }

            }

        }
    }
}