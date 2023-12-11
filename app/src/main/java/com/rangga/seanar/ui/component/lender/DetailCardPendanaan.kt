package com.rangga.seanar.ui.component.lender

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.rangga.seanar.R
import com.rangga.seanar.data.response.FundingData
import com.rangga.seanar.helper.Utils
import com.rangga.seanar.ui.theme.gray_500
import com.rangga.seanar.ui.theme.gray_700
import com.rangga.seanar.ui.theme.primary
import com.rangga.seanar.ui.theme.secondary
import com.rangga.seanar.ui.theme.warning_bold
import com.rangga.seanar.ui.theme.white

@Composable
fun DetailCardPendanaan(data: FundingData) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .offset(y = (-50).dp)
                .shadow(
                    elevation = 10.dp, spotColor = secondary, shape = RoundedCornerShape(8.dp)
                )
                .fillMaxWidth()
                .background(white, RoundedCornerShape(15.dp))
                .padding(20.dp)


        ) {
            Text(text = data.title.toString(), fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Row(modifier = Modifier.padding(vertical = 12.dp)) {
                Text(
                    text = data.organizationName.toString(), fontSize = 14.sp, color = gray_500
                )
            }
            Text(
                text = data.description.toString(),
                fontSize = 12.sp,
                color = gray_500,
                lineHeight = 18.sp
            )

            Row(
                modifier = Modifier.padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "Logo maps",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .width(35.dp),
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = data.location.toString(), fontSize = 13.sp, color = gray_500
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(modifier = Modifier.weight(2F)) {
                    Text(text = "Minimal Pinjaman", fontSize = 13.sp, color = gray_500)
                    Text(
                        text = Utils.formatCurrency(
                            data.minimumLoan?.replace(Regex(".{3}\$"), "")!!.toInt()
                        ), fontSize = 14.sp, color = primary, fontWeight = FontWeight.Bold
                    )
                }
                Column(
                    modifier = Modifier.weight(1F),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Return", fontSize = 13.sp, color = gray_500)
                    Text(
                        text = "${data.jsonMemberReturn.toString()}%",
                        fontSize = 14.sp,
                        color = primary,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(
                    modifier = Modifier.weight(1F),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Grade", fontSize = 13.sp, color = gray_500)
                    Text(
                        text = data.grade.toString(),
                        fontSize = 14.sp,
                        color = primary,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(modifier = Modifier.weight(1F), horizontalAlignment = Alignment.End) {
                    Text(text = "Tenor", fontSize = 13.sp, color = gray_500)
                    Text(
                        text = "${data.duration} Bulan",
                        fontSize = 14.sp,
                        color = primary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Row(modifier = Modifier.padding(top = 16.dp)) {
                Text(
                    text = "Target",
                    fontSize = 13.sp,
                    color = gray_700,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = Utils.formatCurrency(data.targetAmount!!.toInt()), fontSize = 13.sp, color = warning_bold)
            }

        }
    }
}