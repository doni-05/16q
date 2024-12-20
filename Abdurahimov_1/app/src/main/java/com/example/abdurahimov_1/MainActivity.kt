package com.example.abdurahimov_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.abdurahimov_1.ui.theme.Abdurahimov_1Theme
import com.example.Abdurahimov_1Theme.Navigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Abdurahimov_1Theme {

                val navController = rememberNavController()
                Navigation(navController = navController)

            }
        }
    }
}