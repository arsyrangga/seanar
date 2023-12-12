package com.rangga.seanar.ui.screen.borrower

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
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
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.rangga.seanar.data.parcel.BottomSheetParcel
import com.rangga.seanar.data.retrofit.ApiRequest
import com.rangga.seanar.helper.TokenDatastore
import com.rangga.seanar.helper.Utils
import com.rangga.seanar.ui.component.BottomBar
import com.rangga.seanar.ui.component.LoadingComponent
import com.rangga.seanar.ui.component.TopBar
import com.rangga.seanar.ui.component.borrower.BottomButtonFunding
import com.rangga.seanar.ui.component.lender.BottomSheetComponent
import com.rangga.seanar.ui.navigation.detailDonasiLenderScreen
import com.rangga.seanar.ui.navigation.detailPendanaanBorrowerScreen
import com.rangga.seanar.ui.theme.gray_200
import com.rangga.seanar.ui.theme.gray_500
import com.rangga.seanar.ui.theme.gray_600
import com.rangga.seanar.ui.theme.white
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.awaitResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateDonasiBorrowerScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sessionManager = TokenDatastore(context = context)
    val borrower_id = sessionManager.getUserId()
    val userDetail = sessionManager.getDetail()

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    var title by remember {
        mutableStateOf("")
    }

    var pemohon by remember {
        mutableStateOf(userDetail?.username.toString())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    var postId by remember {
        mutableStateOf(1)
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


    var location by remember {
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

            "location" -> {
                location = value
            }
        }
    }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
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

    fun handleClick() {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                showBottomSheet = false
                navController.navigate("$detailDonasiLenderScreen/${postId}")
            }
        }
    }


    fun postDonation() {
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    loading = true

                    val imageFile = Utils.uriToFile(imageUri!!, context)
                    val grade = "2"

                    val borrowerId =
                        borrower_id.toString().toRequestBody("text/plain".toMediaType())
                    val titlex = title.toRequestBody("text/plain".toMediaType())
                    val descriptionx = description.toRequestBody("text/plain".toMediaType())
                    val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
                    val durationx = duration.toRequestBody("text/plain".toMediaType())
                    val targetx = targetAmount.toRequestBody("text/plain".toMediaType())
                    val locationx = location.toRequestBody("text/plain".toMediaType())

                    val multipartBody = MultipartBody.Part.createFormData(
                        "img_file", imageFile.name, requestImageFile
                    )
                    val response = ApiRequest.getApiService(context).postDonation(
                        borrowerId = borrowerId,
                        description = descriptionx,
                        duration = durationx,
                        file = multipartBody,
                        targetAmount = targetx,
                        title = titlex,
                        location = locationx

                    ).awaitResponse()

                    if (response.isSuccessful) {
                        showBottomSheet = true
                        postId = response.body()?.data?.postId!!.toInt()
                    }

                } catch (err: Throwable) {
                    Log.d("TEHX", err.toString())
                } finally {
                    loading = false
                }
            }
        }
    }


    Scaffold(
        bottomBar = { BottomButtonFunding(text = "Buka Donasi", onClick = {postDonation()}, disabled = imageUri.toString().isEmpty() ) },
        topBar = { TopBar(title = "Pembukaan Donasi", navController = navController) },
    ) { innerPadding ->
        LoadingComponent(isLoading = loading)
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
                readOnly = true,
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
                    Text(text = "Masukan Deskripsi Donasi", color = gray_500, fontSize = 14.sp)
                })

            //      Durasi Donasi
            Text(
                text = "Durasi Donasi (Hari)",
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = {
                    Text(text = "Masukan Durasi Donasi", color = gray_500, fontSize = 14.sp)
                })

            //      Target Donasi
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = {
                    Text(text = "Masukan Target Donasi", color = gray_500, fontSize = 14.sp)
                })

            //      Lokasi Donasi
            Text(
                text = "Lokasi Donasi",
                color = gray_600,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 4.dp)
                    .fillMaxWidth(),
                fontSize = 14.sp
            )

            OutlinedTextField(value = location,
                onValueChange = {
                    changeForm("location", it)
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
                    Text(text = "Masukan Lokasi Donasi", color = gray_500, fontSize = 14.sp)
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
            if (showBottomSheet) {
                BottomSheetComponent(data = BottomSheetParcel(
                    title = "Pembukaan Donasi Berhasil",
                    nominal = title,
                    desc = userDetail?.username.toString(),
                ), setShowBottomSheet = { handleClick() }, sheetState = sheetState, onClick = {
                    handleClick()
                }, textButton = "Lihat Donasi"
                )
            }

        }
    }


}