package com.rangga.seanar.ui.screen.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rangga.seanar.ui.component.ButtonComponent
import com.rangga.seanar.ui.theme.gray_200
import com.rangga.seanar.ui.theme.gray_500
import com.rangga.seanar.ui.theme.gray_600
import com.rangga.seanar.ui.theme.primary
import com.rangga.seanar.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterBorrowerScreen(navController: NavController) {
    var valueEmail by remember {
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

    var valueUsername by remember {
        mutableStateOf("")
    }

    fun changeEmail(newEmail: String) {
        valueEmail = newEmail
    }

    fun changeNik(newNik: String) {
        valueNik = newNik
    }

    fun changePassword(newPassword: String) {
        valuePassword = newPassword
    }

    fun changePhone(newPhone: String) {
        valuePhone = newPhone
    }

    fun changeUsername(newUsername: String) {
        valueUsername = newUsername
    }

//  Code
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

        OutlinedTextField(
            value = valueEmail,
            onValueChange = {
                changeEmail(it)
            },

            modifier = Modifier
                .background(color = gray_200, shape = RoundedCornerShape(8.dp))
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = gray_200,
                unfocusedBorderColor = white
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeholder = {
                Text(text = "Masukan Email", color = gray_500)
            }
        )

        //      input NIK
        Text(
            text = "NIK",
            color = gray_600,
            modifier = Modifier
                .padding(bottom = 4.dp)
                .fillMaxWidth(),
            fontSize = 14.sp
        )

        OutlinedTextField(
            value = valueNik,
            onValueChange = {
                changeNik(it)
            },

            modifier = Modifier
                .background(color = gray_200, shape = RoundedCornerShape(8.dp))
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = gray_200,
                unfocusedBorderColor = white
            ),
            placeholder = {
                Text(text = "Masukan NIK", color = gray_500)
            }
        )

        //  Nomor HP
        Text(
            text = "Nomor HP",
            color = gray_600,
            modifier = Modifier
                .padding(bottom = 4.dp, top = 16.dp)
                .fillMaxWidth(),
            fontSize = 14.sp
        )

        OutlinedTextField(
            value = valueEmail,
            onValueChange = {
                changePhone(it)
            },

            modifier = Modifier
                .background(color = gray_200, shape = RoundedCornerShape(8.dp))
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = gray_200,
                unfocusedBorderColor = white
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            placeholder = {
                Text(text = "Masukan Nomor HP", color = gray_500)
            }
        )

        //  username
        Text(
            text = "Username",
            color = gray_600,
            modifier = Modifier
                .padding(bottom = 4.dp, top = 16.dp)
                .fillMaxWidth(),
            fontSize = 14.sp
        )

        OutlinedTextField(
            value = valueUsername,
            onValueChange = {
                changeUsername(it)
            },

            modifier = Modifier
                .background(color = gray_200, shape = RoundedCornerShape(8.dp))
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = gray_200,
                unfocusedBorderColor = white
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            placeholder = {
                Text(text = "Masukan Username", color = gray_500)
            }
        )

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
                .background(color = gray_200, shape = RoundedCornerShape(8.dp))
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = gray_200,
                unfocusedBorderColor = white
            ),
            placeholder = {
                Text(text = "Masukan Password", color = gray_500)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()

        )

        Row(
            modifier = Modifier
                .fillMaxWidth().padding(top = 4.dp), horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Daftar Sebagai ",
                fontSize = 14.sp,
                color = gray_600,
                modifier = Modifier
            )

            ClickableText(
                text = AnnotatedString("Pemberi Pinjaman?"),
                onClick = {
                    navController.navigate("register_lender_screen")
                },
                style = TextStyle(color = primary, fontSize = 14.sp)
            )
        }



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Sudah punya Akun? ",
                fontSize = 14.sp,

                )

            ClickableText(
                text = AnnotatedString("Masuk"),
                onClick = {
                    navController.navigate("login_screen")
                },
                style = TextStyle(color = primary, fontSize = 14.sp)
            )
        }

        ButtonComponent(onClick = {
            // Navigate to the main screen
        }, text = "Daftar", modifier = Modifier.padding(top = 16.dp))


    }
}