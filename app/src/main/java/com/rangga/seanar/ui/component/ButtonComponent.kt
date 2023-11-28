package com.rangga.seanar.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rangga.seanar.ui.theme.primary

@Composable
fun ButtonComponent(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier,
    disabled: Boolean = false,
    fontSize : TextUnit = 14.sp
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = primary),
        modifier = modifier.fillMaxWidth(), enabled = !disabled
    ) {
        Text(text = text, modifier = Modifier.padding(vertical = 8.dp), fontSize = fontSize)
    }
}