package com.rangga.seanar.ui.component.lender

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rangga.seanar.R
import com.rangga.seanar.ui.theme.primaryDark
import com.rangga.seanar.ui.theme.secondary

@Composable
fun CardHomeLender(
    modifier: Modifier = Modifier,
    image: Painter,
    title: String,
    description: String,
) {
    Box(
        modifier = modifier
            .width(180.dp)
            .offset(y = (-30).dp)
            .background(secondary, RoundedCornerShape(15.dp))
            .height(130.dp)
            .padding(top = 15.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
    ) {
        Column {
            Image(
                painter = image,
                contentDescription = "Image Box Home",
                modifier = Modifier.height(height = 40.dp),
                contentScale = ContentScale.FillHeight,

            )
            Text(
                text = title,
                color = primaryDark,
                modifier = Modifier.padding(top = 6.dp, bottom = 4.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                text = description,
                color = primaryDark,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}