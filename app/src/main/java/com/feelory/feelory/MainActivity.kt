package com.feelory.feelory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.feelory.feelory.feature.home.navigation.HOME_ROUTE
import com.feelory.feelory.ui.theme.FeeloryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FeeloryTheme {
                FeeloryApp(
                    startDestination = HOME_ROUTE
                )
            }
        }
    }
}
