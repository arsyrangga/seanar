package com.rangga.seanar.ui.screen.onboarding

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.rangga.seanar.data.parcel.BottomSheetParcel
import com.rangga.seanar.data.parcel.RegisterBorrowerParcel
import com.rangga.seanar.data.retrofit.ApiRequest
import com.rangga.seanar.ui.component.ButtonComponent
import com.rangga.seanar.ui.component.lender.BottomSheetComponent
import com.rangga.seanar.ui.navigation.detailPendanaanBorrowerScreen
import com.rangga.seanar.ui.navigation.loginScreen
import com.rangga.seanar.ui.theme.gray_200
import com.rangga.seanar.ui.theme.gray_500
import com.rangga.seanar.ui.theme.gray_600
import com.rangga.seanar.ui.theme.primary
import com.rangga.seanar.ui.theme.white
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterBorrowerScreen(navController: NavController) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val options = listOf("ORGANIZATION", "COMPANY")
    var expanded by remember { mutableStateOf(false) }
    var role by remember { mutableStateOf(options[0]) }
    val sheetState = rememberModalBottomSheetState()


    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    var valueEmail by remember {
        mutableStateOf("")
    }

    var valueIncome by remember {
        mutableStateOf("")
    }

    var valueNik by remember {
        mutableStateOf("")
    }

    var valuePassword by remember {
        mutableStateOf("")
    }

    var valuePhone by remember {
        mutableStateOf("")
    }

    var valueName by remember {
        mutableStateOf("")
    }
    var valueAddress by remember {
        mutableStateOf("")
    }

    var averageAnnualRevenue by remember {
        mutableStateOf("")
    }

    var averageCatchWeight by remember {
        mutableStateOf("")
    }

    var yearsInBusiness by remember {
        mutableStateOf("")
    }

    var numberOfEmployees by remember {
        mutableStateOf("")
    }

    var numberOfShips by remember {
        mutableStateOf("")
    }

    var loading by remember {
        mutableStateOf(false)
    }

    fun changeAverageCatchWeight(newAverageCatchWeight: String) {
        averageCatchWeight = newAverageCatchWeight.replace("[^0-9.]".toRegex(), "")
    }


    fun changeAverageAnnualRevenue(newAverageAnnualRevenue: String) {
        averageAnnualRevenue = newAverageAnnualRevenue.replace("[^0-9.]".toRegex(), "")
    }

    fun changeYearsInBusiness(newYearsInBusiness: String) {
        yearsInBusiness = newYearsInBusiness.replace("[^0-9.]".toRegex(), "")
    }

    fun changeNumberOfEmployees(newNumberOfEmployees: String) {
        numberOfEmployees = newNumberOfEmployees.replace("[^0-9]".toRegex(), "")
    }

    fun changeNumberOfShips(newNumberOfShips: String) {
        numberOfShips = newNumberOfShips.replace("[^0-9.]".toRegex(), "")
    }

    fun handleClick() {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                showBottomSheet = false
            }
        }
    }

    fun resetRole() {
        changeAverageCatchWeight("")
        changeAverageAnnualRevenue("")
        changeYearsInBusiness("")
        changeNumberOfEmployees("")
        changeNumberOfShips("")
    }


    fun changeEmail(newEmail: String) {
        valueEmail = newEmail
    }

    fun changeNik(newNik: String) {
        valueNik = newNik.replace("[^0-9]".toRegex(), "")
    }

    fun changePassword(newPassword: String) {
        valuePassword = newPassword
    }

    fun changePhone(newPhone: String) {
        valuePhone = newPhone.replace("[^0-9]".toRegex(), "")
    }

    fun changeIncome(newIncome: String) {
        valueIncome = newIncome.replace("[^0-9]".toRegex(), "")
    }

    fun changeName(newName: String) {
        valueName = newName
    }

    fun changeAddress(newAddress: String) {
        valueAddress = newAddress
    }

    fun handleRegister() {
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    loading = true
                    val response = ApiRequest.getApiService(context).registerBorrower(
                        RegisterBorrowerParcel(
                            email = valueEmail,
                            nik = valueNik,
                            phone_number = valuePhone,
                            organization_name = valueName,
                            password = valuePassword,
                            credit_score_category = "9",
                            income = valueIncome.toInt(),
                            organization_address = valueAddress,
                            role = role,
                            average_annual_revenue = averageAnnualRevenue.toInt(),
                            average_catch_weight = averageCatchWeight.toInt(),
                            years_in_business = yearsInBusiness.toInt(),
                            number_of_employees = numberOfEmployees.toInt(),
                            number_of_ships = numberOfShips.toInt()
                        )
                    ).awaitResponse()
                    if (response.isSuccessful()) {
                        Toast.makeText(
                            context, "Berhasil Daftar, Silahkan Login", Toast.LENGTH_SHORT
                        ).show()
                        navController.navigate(loginScreen)
                    } else {
                        if (response.code() == 403) {
                            showBottomSheet = true
                        }
                    }
                } catch (err: Throwable) {

                } finally {
                    loading = false
                }
            }
        }
    }


    val disabled = if (role == "ORGANIZATION") {
        !(valueEmail.isNotEmpty() && valueNik.isNotEmpty() && valuePhone.isNotEmpty() && valueIncome.isNotEmpty() && valueAddress.isNotEmpty() && valueName.isNotEmpty() && valuePassword.isNotEmpty())
    } else {
        !(valueEmail.isNotEmpty() && valueNik.isNotEmpty() && valuePhone.isNotEmpty() && valueIncome.isNotEmpty() && valueAddress.isNotEmpty() && valueName.isNotEmpty() && valuePassword.isNotEmpty() && averageCatchWeight.isNotEmpty() && averageAnnualRevenue.isNotEmpty() && yearsInBusiness.isNotEmpty() && numberOfEmployees.isNotEmpty() && numberOfShips.isNotEmpty())
    }



//  Code
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Daftar",
                color = primary,
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "Sebagai Peminjam (Borrower)",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 24.dp, top = 4.dp)
                    .fillMaxWidth(),
                fontSize = 12.sp,

                )

//      input email
            Text(
                text = "Email",
                color = gray_600,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .fillMaxWidth(),
                fontSize = 14.sp
            )

            OutlinedTextField(value = valueEmail,
                onValueChange = {
                    changeEmail(it)
                },

                modifier = Modifier
                    .background(
                        color = gray_200, shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = gray_200, unfocusedBorderColor = white
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                placeholder = {
                    Text(text = "Masukan Email", color = gray_500)
                })

            //      input NIK
            Text(
                text = "NIK",
                color = gray_600,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .fillMaxWidth(),
                fontSize = 14.sp
            )

            OutlinedTextField(value = valueNik,
                onValueChange = {
                    changeNik(it)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .background(
                        color = gray_200, shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = gray_200, unfocusedBorderColor = white
                ),
                placeholder = {
                    Text(text = "Masukan NIK", color = gray_500)
                })

            //  Nomor HP
            Text(
                text = "Nomor HP",
                color = gray_600,
                modifier = Modifier
                    .padding(bottom = 4.dp, top = 16.dp)
                    .fillMaxWidth(),
                fontSize = 14.sp
            )

            OutlinedTextField(value = valuePhone,
                onValueChange = {
                    changePhone(it)
                },

                modifier = Modifier
                    .background(
                        color = gray_200, shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = gray_200, unfocusedBorderColor = white
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                placeholder = {
                    Text(text = "Masukan Nomor HP", color = gray_500)
                })

            //  Penghasilan
            Text(
                text = "Penghasilan",
                color = gray_600,
                modifier = Modifier
                    .padding(bottom = 4.dp, top = 16.dp)
                    .fillMaxWidth(),
                fontSize = 14.sp
            )

            OutlinedTextField(value = valueIncome,
                onValueChange = {
                    changeIncome(it)
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
                    Text(text = "Masukan Penghasilan", color = gray_500)
                })

            //  Alamat
            Text(
                text = "Alamat",
                color = gray_600,
                modifier = Modifier
                    .padding(bottom = 4.dp, top = 16.dp)
                    .fillMaxWidth(),
                fontSize = 14.sp
            )

            OutlinedTextField(value = valueAddress,
                onValueChange = {
                    changeAddress(it)
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
                    Text(text = "Masukan Alamat", color = gray_500)
                })

            //  username
            Text(
                text = "Nama",
                color = gray_600,
                modifier = Modifier
                    .padding(bottom = 4.dp, top = 16.dp)
                    .fillMaxWidth(),
                fontSize = 14.sp
            )

            OutlinedTextField(value = valueName,
                onValueChange = {
                    changeName(it)
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
                    Text(text = "Masukan Nama", color = gray_500)
                })

            //      input Password
            Text(
                text = "Password",
                color = gray_600,
                modifier = Modifier
                    .padding(bottom = 4.dp, top = 16.dp)
                    .fillMaxWidth(),
                fontSize = 14.sp
            )

            OutlinedTextField(
                value = valuePassword,
                onValueChange = {
                    changePassword(it)
                },

                modifier = Modifier
                    .background(
                        color = gray_200, shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = gray_200, unfocusedBorderColor = white
                ),
                placeholder = {
                    Text(text = "Masukan Password", color = gray_500)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()

            )

            // type
            Text(
                text = "Role",
                color = gray_600,
                modifier = Modifier
                    .padding(bottom = 4.dp, top = 16.dp)
                    .fillMaxWidth(),
                fontSize = 14.sp
            )
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
            ) {
                OutlinedTextField(
                    // The `menuAnchor` modifier must be passed to the text field for correctness.
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .background(color = gray_200),
                    readOnly = true,
                    value = role,
                    onValueChange = {},
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = gray_200, unfocusedBorderColor = white
                    ),

                    )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    },
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                role = selectionOption
                                expanded = false
                                resetRole()
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
            }

            // Logic
            if (role == "COMPANY") {
                // rata rata Pendapatan
                Text(
                    text = "Pendapatan rata rata pertahun",
                    color = gray_600,
                    modifier = Modifier
                        .padding(bottom = 4.dp, top = 16.dp)
                        .fillMaxWidth(),
                    fontSize = 14.sp
                )

                OutlinedTextField(value = averageAnnualRevenue,
                    onValueChange = {
                        changeAverageAnnualRevenue(it)
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
                        Text(text = "Masukan Pendapatan rata rata pertahun", color = gray_500)
                    })

                // rata rata Berat tangkapan
                Text(
                    text = "rata rata Berat tangkapan (KG)",
                    color = gray_600,
                    modifier = Modifier
                        .padding(bottom = 4.dp, top = 16.dp)
                        .fillMaxWidth(),
                    fontSize = 14.sp
                )

                OutlinedTextField(value = averageCatchWeight,
                    onValueChange = {
                        changeAverageCatchWeight(it)
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
                        Text(text = "Masukan rata rata Berat tangkapan", color = gray_500)
                    })

                // Lama Dalam Bisnis
                Text(
                    text = "Lama dalam bisnis (tahun)",
                    color = gray_600,
                    modifier = Modifier
                        .padding(bottom = 4.dp, top = 16.dp)
                        .fillMaxWidth(),
                    fontSize = 14.sp
                )

                OutlinedTextField(value = yearsInBusiness,
                    onValueChange = {
                        changeYearsInBusiness(it)
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
                        Text(text = "Masukan Lama dalam bisnis", color = gray_500)
                    })

                // Jumlah Karyawan
                Text(
                    text = "Jumlah Karyawan",
                    color = gray_600,
                    modifier = Modifier
                        .padding(bottom = 4.dp, top = 16.dp)
                        .fillMaxWidth(),
                    fontSize = 14.sp
                )

                OutlinedTextField(value = numberOfEmployees,
                    onValueChange = {
                        changeNumberOfEmployees(it)
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
                        Text(text = "Masukan Jumlah Karyawan", color = gray_500)
                    })

                // Jumlah Kapal
                Text(
                    text = "Total Kapal",
                    color = gray_600,
                    modifier = Modifier
                        .padding(bottom = 4.dp, top = 16.dp)
                        .fillMaxWidth(),
                    fontSize = 14.sp
                )

                OutlinedTextField(value = numberOfShips,
                    onValueChange = {
                        changeNumberOfShips(it)
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
                        Text(text = "Masukan Total Kapal", color = gray_500)
                    })
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Daftar Sebagai ",
                    fontSize = 14.sp,
                    color = gray_600,
                    modifier = Modifier
                )

                ClickableText(
                    text = AnnotatedString("Pemberi Pinjaman?"), onClick = {
                        navController.navigate("register_lender_screen")
                    }, style = TextStyle(color = primary, fontSize = 14.sp)
                )
            }



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Sudah punya Akun? ",
                    fontSize = 14.sp,

                    )

                ClickableText(
                    text = AnnotatedString("Masuk"), onClick = {
                        navController.navigate("login_screen")
                    }, style = TextStyle(color = primary, fontSize = 14.sp)
                )
            }

            ButtonComponent(
                disabled = disabled, onClick = {
                    handleRegister()
                }, text = "Daftar", modifier = Modifier.padding(top = 16.dp)
            )

            if (showBottomSheet) {
                BottomSheetComponent(
                    data = BottomSheetParcel(
                        title = "Registrasi Gagal",
                        nominal = "Anda terindikasi penipuan",
                        desc = "",
                    ),
                    setShowBottomSheet = { handleClick() },
                    sheetState = sheetState,
                    type = "failed",
                    onClick = {
                        handleClick()
                    },
                    textButton = "Tutup"
                )
            }
        }
    }

}