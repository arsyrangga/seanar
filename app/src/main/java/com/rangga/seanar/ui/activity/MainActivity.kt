package com.rangga.seanar.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.rangga.seanar.ui.screen.onboarding.SplashScreen
import com.rangga.seanar.ui.navigation.SeanarNavigation
import com.rangga.seanar.ui.theme.SeanarTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SeanarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    SeanarNavigation()
                }
            }
        }
    }
}

@Composable
//fun SeanarApp(modifier: Modifier = Modifier) {
//    Scaffold(bottomBar = { BottomBar() }) { innerPadding ->
//        Column(
//            modifier = modifier
//                .verticalScroll(rememberScrollState())
//                .padding(innerPadding)
//        ) {
//            SplashScreen()
//        }
//    }
//}



@Preview(showBackground = true)
fun Previews() {
    SeanarTheme {
        SeanarNavigation()
    }
}