package com.vehojo.schejul

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vehojo.schejul.ui.components.Footer
import com.vehojo.schejul.ui.components.Header
import com.vehojo.schejul.ui.screens.HomeScreen
import com.vehojo.schejul.ui.theme.SchejulTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SchejulTheme {
                Main()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Main() {
    Column() {
        HomeScreen()
    }
}

