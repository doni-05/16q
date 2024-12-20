package com.example.Abdurahimov_1Theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.abdurahimov_1.R
import kotlinx.coroutines.delay

@Composable
fun splash(navController: NavController){

    var isLoading by remember { mutableStateOf(true) }
    // Запуск корутины для имитации загрузки данных
    LaunchedEffect(Unit) {
        delay(3000) // Имитация задержки загрузки данных
        isLoading = false
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (isLoading) {
            // Отображение изображения загрузки
            Image(
                painter = painterResource(id = R.drawable.wear_me),
                contentDescription = "Loading",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
        else {
            // Отображение основного контента после загрузки
           // OnboardingScreen(navController = navController)
            mainscreen(navController = navController)
        }
    }
}