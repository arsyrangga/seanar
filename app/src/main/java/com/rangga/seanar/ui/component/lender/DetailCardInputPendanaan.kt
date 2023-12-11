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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rangga.seanar.R
import com.rangga.seanar.ui.component.ButtonComponent
import com.rangga.seanar.ui.theme.gray_200
import com.rangga.seanar.ui.theme.gray_500
import com.rangga.seanar.ui.theme.gray_700
import com.rangga.seanar.ui.theme.primary
import com.rangga.seanar.ui.theme.secondary
import com.rangga.seanar.ui.theme.warning_bold
import com.rangga.seanar.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailCardInputPendanaan(nominal : String, changeNominal: (String) -> Unit) {



    Box(
        modifier = Modifier
            .offset(y = (-25).dp)
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .shadow(
                    elevation = 10.dp, spotColor = secondary, shape = RoundedCornerShape(8.dp)
                )
                .fillMaxWidth()
                .background(white, RoundedCornerShape(15.dp))
                .padding(20.dp)
        ) {
            Text(text = "Pendaaan Mitra", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Nominal ", modifier = Modifier.weight(1F), fontSize = 14.sp)
                OutlinedTextField(value = nominal,
                    onValueChange = {
                      changeNominal(it)
                    },
                    maxLines = 1,
                    modifier = Modifier
                        .background(
                            color = gray_200, shape = RoundedCornerShape(8.dp)
                        )
                        .weight(1F),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = gray_200, unfocusedBorderColor = white
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    placeholder = {
                        Text(text = "Masukan Nominal", color = gray_500, fontSize = 14.sp)
                    })
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Prediksi Keuntungan", modifier = Modifier.weight(1F), fontSize = 12.sp)
                Text(
                    text = "Rp500.000,00 ",
                    modifier = Modifier.weight(1F),
                    fontSize = 12.sp,
                    color = warning_bold,
                    textAlign = TextAlign.End
                )

            }
        }

    }
}