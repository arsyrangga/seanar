package com.rangga.seanar.ui.component.lender

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
import com.rangga.seanar.data.parcel.DetailUserParcel
import com.rangga.seanar.ui.theme.primary
import com.rangga.seanar.ui.theme.white

@Composable
fun HeaderProfileLender(
    modifier: Modifier = Modifier,
    data : DetailUserParcel?
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
                    .weight(1F),
                verticalArrangement = Arrangement.Center,
            ) {
                Row(modifier = Modifier.fillMaxWidth(0.8F)) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile Image Home",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth,
                    )
                }

            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2F),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = data?.username.toString(),
                    color = white,
                    fontSize = 24.sp,
                )
                Text(
                     text = data?.email.toString(),
                    color = white,
                    modifier = Modifier.padding(top = 4.dp, bottom = 6.dp)
                )
                Text(
                    text = data?.phoneNumber.toString(),
                    color = white,
                )
            }
        }
    }
}