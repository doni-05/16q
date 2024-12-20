package com.example.Abdurahimov_1Theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.mainscreen.route
    ) {
        composable(route = Screen.mainscreen.route) {
            mainscreen(navController = navController)
        }
        composable(route = Screen.mainscreen.route) {
            mainscreen(navController = navController)
        }
    }
}