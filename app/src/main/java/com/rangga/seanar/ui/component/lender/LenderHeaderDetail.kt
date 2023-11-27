package com.rangga.seanar.ui.component.lender

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rangga.seanar.ui.theme.primary

@Composable
fun LenderHeaderDetail(){
    Box(
        modifier = Modifier
            .height(175.dp)
            .background(primary)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = rememberAsyncImagePainter(model = "https://picsum.photos/seed/251/900"),
            contentDescription = "image_detail",
            contentScale = ContentScale.FillWidth
        )
    }
}