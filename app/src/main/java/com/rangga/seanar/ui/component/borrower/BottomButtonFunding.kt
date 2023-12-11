package com.rangga.seanar.ui.component.borrower

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.rangga.seanar.ui.component.ButtonComponent

@Composable
fun BottomButtonFunding(disabled: Boolean = false, text : String, onClick: ()-> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 0.dp, bottom = 16.dp, start = 16.dp, end = 16.dp).shadow(
                elevation = 3.dp,
                RoundedCornerShape(16.dp)
            )
    ) {
        ButtonComponent(
            disabled = disabled,
            onClick = onClick,
            text = text,
            modifier = Modifier
        )
    }
}
