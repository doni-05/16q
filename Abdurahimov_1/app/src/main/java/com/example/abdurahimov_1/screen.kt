package com.example.Abdurahimov_1Theme


sealed class Screen(val route: String) {
    object mainscreen : Screen("mainsvreen")
    object splash : Screen("splash")
}