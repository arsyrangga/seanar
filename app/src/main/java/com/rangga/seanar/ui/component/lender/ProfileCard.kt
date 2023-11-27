package com.rangga.seanar.ui.component.lender

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rangga.seanar.R

@Composable
fun ProfileCard(icon: Int, title: String, color: Color, onClick: () -> Unit) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .height(60.dp).padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.weight(3F), verticalAlignment = Alignment.CenterVertically) {
            Row(modifier = Modifier.width(30.dp)) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "Profile",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Text(text = title, color = color)
            }
        }
        Row(modifier = Modifier.weight(1F), horizontalArrangement = Arrangement.End) {
            Image(
                painter = painterResource(id = R.drawable.chevron_right),
                contentDescription = "Chevron",
                modifier = Modifier.size(30.dp),
                contentScale = ContentScale.Fit
            )
        }

    }
}