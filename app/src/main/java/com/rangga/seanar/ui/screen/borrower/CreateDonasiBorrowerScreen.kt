package com.rangga.seanar.ui.screen.borrower

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rangga.seanar.R
import com.rangga.seanar.ui.component.BottomBar
import com.rangga.seanar.ui.component.TopBar
import com.rangga.seanar.ui.component.borrower.BottomButtonFunding
import com.rangga.seanar.ui.theme.gray_200
import com.rangga.seanar.ui.theme.gray_500
import com.rangga.seanar.ui.theme.gray_600
import com.rangga.seanar.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateDonasiBorrowerScreen(navController: NavController) {

    var title by remember {
        mutableStateOf("")
    }

    var pemohon by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var duration by remember {
        mutableStateOf("")
    }

    var targetAmount by remember {
        mutableStateOf("")
    }


    fun changeForm(name: String, value: String) {
        when (name) {
            "title" -> {
                title = value
            }

            "pemohon" -> {
                pemohon = value
            }

            "description" -> {
                description = value
            }

            "duration" -> {
                duration = value
            }

            "target_amount" -> {
                targetAmount = value
            }
        }
    }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    fun handlePick() {
        launcher.launch("image/*")
    }




    Scaffold(
        bottomBar = { BottomButtonFunding(text = "Buka Donasi", onClick = {}) },
        topBar = { TopBar(title = "Pembukaan Donasi", navController = navController) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
//      Nama Pendanaan
            Text(
                text = "Nama Donasi",
                color = gray_600,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .fillMaxWidth(),
                fontSize = 14.sp
            )

            OutlinedTextField(value = title,
                onValueChange = {
                    changeForm("title", it)
                },

                modifier = Modifier
                    .background(
                        color = gray_200, shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = gray_200, unfocusedBorderColor = white
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                placeholder = {
                    Text(text = "Masukan Nama Donasi", color = gray_500, fontSize = 14.sp)
                })

            //      Pemohon Pendanaan
            Text(
                text = "Pemohon Donasi",
                color = gray_600,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .fillMaxWidth(),
                fontSize = 14.sp
            )

            OutlinedTextField(value = pemohon,
                onValueChange = {
                    changeForm("pemohon", it)
                },

                modifier = Modifier
                    .background(
                        color = gray_200, shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = gray_200, unfocusedBorderColor = white
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                placeholder = {
                    Text(text = "Masukan Pemohon Donasi", color = gray_500, fontSize = 14.sp)
                })


            //      Deskripsi Pendanaan
            Text(
                text = "Deskripsi Donasi",
                color = gray_600,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 4.dp)
                    .fillMaxWidth(),
                fontSize = 14.sp
            )

            OutlinedTextField(value = description,
                onValueChange = {
                    changeForm("description", it)
                },

                modifier = Modifier
                    .background(
                        color = gray_200, shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = gray_200, unfocusedBorderColor = white
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                placeholder = {
                    Text(text = "Masukan Donasi Pendanaan", color = gray_500, fontSize = 14.sp)
                })

            //      Durasi Pendanaan
            Text(
                text = "Durasi Donasi (Bulan)",
                color = gray_600,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 4.dp)
                    .fillMaxWidth(),
                fontSize = 14.sp
            )

            OutlinedTextField(value = duration,
                onValueChange = {
                    changeForm("duration", it)
                },

                modifier = Modifier
                    .background(
                        color = gray_200, shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = gray_200, unfocusedBorderColor = white
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                placeholder = {
                    Text(text = "Masukan Durasi Pendanaan", color = gray_500, fontSize = 14.sp)
                })

            //      Target Pendanaan
            Text(
                text = "Target Donasi",
                color = gray_600,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 4.dp)
                    .fillMaxWidth(),
                fontSize = 14.sp
            )

            OutlinedTextField(value = targetAmount,
                onValueChange = {
                    changeForm("target_amount", it)
                },

                modifier = Modifier
                    .background(
                        color = gray_200, shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = gray_200, unfocusedBorderColor = white
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                placeholder = {
                    Text(text = "Masukan Target Pendanaan", color = gray_500, fontSize = 14.sp)
                })


            //    Unggah Foto
            Text(
                text = "Unggah Cover",
                color = gray_600,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 4.dp)
                    .fillMaxWidth(),
                fontSize = 14.sp
            )
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(color = gray_200, shape = RoundedCornerShape(8.dp))
                .clickable { handlePick() }
                .padding(vertical = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                if (imageUri != null) {
                    imageUri?.let {
                        if (Build.VERSION.SDK_INT < 28) {
                            bitmap.value =
                                MediaStore.Images.Media.getBitmap(context.contentResolver, it)

                        } else {
                            val source = ImageDecoder.createSource(context.contentResolver, it)
                            bitmap.value = ImageDecoder.decodeBitmap(source)
                        }

                        bitmap.value?.let { btm ->
                            Image(
                                bitmap = btm.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier.size(400.dp)
                            )
                        }
                    }
                } else {
                    Image(
                        painter = painterResource(R.drawable.image_icon),
                        contentDescription = "Image ICON"
                    )
                    Text(text = "Pilih Gambar", color = gray_500, fontSize = 14.sp)
                }
            }

        }
    }


}