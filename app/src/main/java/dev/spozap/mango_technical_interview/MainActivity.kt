package dev.spozap.mango_technical_interview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.spozap.design_system.MangotechnicalinterviewTheme
import dev.spozap.mango_technical_interview.navigation.AppNavHost

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MangotechnicalinterviewTheme {
                AppNavHost(navController = rememberNavController())
            }
        }
    }
}