package com.example.pr17

sealed class Screen(val route: String) {
    object LoginUI : Screen("login")
    object signup : Screen("signup")
    object google1 : Screen("google1")
    object MainActivity : Screen("mainActivity")
    object History :Screen("History")
    object Setting:Screen("setting")
    object WaiTimer :Screen("WaitTimer")
    object google:Screen("Google")
    object DriverTimerEnd : Screen("DrivertimerEnd")

}