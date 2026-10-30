package com.vehojo.schejul
import androidx.activity.compose.setContent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.vehojo.schejul.ui.screens.HomeScreen
import com.vehojo.schejul.ui.theme.SchejulTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SchejulTheme {
                HomeScreen()
            }
        }
    }
}
