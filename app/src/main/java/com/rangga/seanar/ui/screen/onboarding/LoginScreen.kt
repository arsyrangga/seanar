package com.rangga.seanar.ui.screen.onboarding

import android.annotation.SuppressLint
import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.rangga.seanar.R
import com.rangga.seanar.data.parcel.LoginParcel
import com.rangga.seanar.data.retrofit.ApiRequest
import com.rangga.seanar.helper.TokenDatastore
import com.rangga.seanar.ui.component.ButtonComponent
import com.rangga.seanar.ui.component.LoadingComponent
import com.rangga.seanar.ui.navigation.homeBorrowerScreen
import com.rangga.seanar.ui.navigation.homeLenderScreen
import com.rangga.seanar.ui.navigation.loginScreen
import com.rangga.seanar.ui.theme.black
import com.rangga.seanar.ui.theme.gray_200
import com.rangga.seanar.ui.theme.gray_300
import com.rangga.seanar.ui.theme.gray_400
import com.rangga.seanar.ui.theme.gray_500
import com.rangga.seanar.ui.theme.gray_600
import com.rangga.seanar.ui.theme.primary
import com.rangga.seanar.ui.theme.white
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import java.util.logging.Handler

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val sessionManager = TokenDatastore(context = context)

    fun goLender() {
        navController.navigate(homeLenderScreen) {
            popUpTo(loginScreen) {
                inclusive = true
            }
        }
    }

    fun goBorrower() {
        navController.navigate(homeBorrowerScreen) {
            popUpTo(loginScreen) {
                inclusive = true
            }
        }
    }


    var isLoad by remember {
        mutableStateOf(false)
    }


    var loading by remember {
        mutableStateOf(false)
    }

    var valueEmail by remember {
        mutableStateOf("")
    }

    var valuePassword by remember {
        mutableStateOf("")
    }

    fun changeEmail(newEmail: String) {
        valueEmail = newEmail
    }

    fun changePassword(newPassword: String) {
        valuePassword = newPassword
    }

    LaunchedEffect(key1 = true) {
        if (sessionManager.getToken()?.isNotEmpty() == true) {
            if (sessionManager.getRole() == "LENDER") {
                goLender()
            } else {
                goBorrower()
            }
        } else{
            isLoad = true
        }
    }

    fun handleLogin() {
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    loading = true
                    val response = ApiRequest.getApiService(context)
                        .login(LoginParcel(email = valueEmail, password = valuePassword))
                        .awaitResponse()

                    if (response.isSuccessful) {
                        val role = if (response.body()?.data?.isLender == true) {
                            "LENDER"
                        } else {
                            "BORROWER"
                        }
                        sessionManager.saveToken(response.body()?.accessToken.toString())
                        sessionManager.saveRole(role)
                        sessionManager.saveUserId(response.body()?.data?.userId.toString())
                        if (response.body()?.data?.isLender == true) {
                            goLender()
                        } else {
                            goBorrower()
                        }

                    } else {
                        android.os.Handler(Looper.getMainLooper()).post {
                            Toast.makeText(
                                context,
                                "Login Gagal, Silahkan Cek Kembali Username & Password Anda",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch (err: Throwable) {
                    android.os.Handler(Looper.getMainLooper()).post {
                        Toast.makeText(
                            context,
                            "Login Gagal, Silahkan Cek Kembali Username & Password Anda",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } finally {
                    loading = false
                }

            }
        }
    }

//  Code
    if (isLoad) {
        Scaffold {
            LoadingComponent(loading)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 18.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth,
                    painter = painterResource(id = R.drawable.login_image),
                    contentDescription = stringResource(
                        id = R.string.login_image
                    )
                )
                Text(
                    text = "Masuk",
                    color = primary,
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 32.dp, bottom = 24.dp)
                        .fillMaxWidth()
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
                    maxLines = 1,
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
                    maxLines = 1,
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



                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Belum punya Akun? ",
                        fontSize = 14.sp,

                        )

                    ClickableText(
                        text = AnnotatedString("Daftar"), onClick = {
                            navController.navigate("register_lender_screen")
                        }, style = TextStyle(color = primary, fontSize = 14.sp)
                    )
                }

                ButtonComponent(onClick = {
                    handleLogin()
                }, text = "Masuk", modifier = Modifier.padding(top = 16.dp))
            }
        }
    }


}

