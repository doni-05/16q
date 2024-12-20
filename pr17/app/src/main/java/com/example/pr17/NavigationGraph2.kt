package com.example.pr17


import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pr17.pr17.DriverTimeEbd
import com.example.pr17.pr17.History
import com.example.pr17.pr17.LoginUI
import com.example.pr17.pr17.Settig
import com.example.pr17.pr17.Signiup1
import com.example.pr17.pr17.WaitTimer
import com.example.pr17.pr17.signin1
import kotlinx.coroutines.launch

@Composable
fun Navigation(navController: NavHostController, requestPermissionLauncher: ActivityResultLauncher<String>) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    NavHost(
        navController = navController,
        startDestination = Screen.LoginUI.route
    ) {
        composable(route = Screen.LoginUI.route) {
            signin1(navController = navController)
        }
        composable(route = Screen.signup.route) {
            Signiup1(navController = navController)
        }
        composable(route = Screen.google1.route) {
            // Добавьте содержимое для google1, если необходимо
        }
        composable(route = Screen.MainActivity.route) {
            MainActivityContent(navController = navController, requestPermissionLauncher = requestPermissionLauncher)
        }
        composable(route = Screen.History.route) {
            History()
        }
        composable(route = Screen.Setting.route) {
            Settig(navController = navController)
        }
        composable(route = Screen.WaiTimer.route) {
            WaitTimer(navController = navController)
        }
        composable(route = Screen.google.route) {
            google(drawerState, scope, navController = navController, requestPermissionLauncher = requestPermissionLauncher)
        }
        composable(route = Screen.DriverTimerEnd.route) {
            DriverTimeEbd()
        }
    }
}

@Composable
fun MainActivityContent(navController: NavController, requestPermissionLauncher: ActivityResultLauncher<String>) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(200.dp) // Установите нужную ширину
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.background(colorResource(R.color.фон1)).fillMaxSize().padding(start = 15.dp),
                ) {
                    // Иконка закрытия справа
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            modifier = Modifier.padding(top = 16.dp, end = 16.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.menu_q5wpwvho6gh3),
                                contentDescription = "Меню"
                            )
                        }
                    }

                    // Профиль
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        IconButton(
                            onClick = {},
                        ) {
                            Image(
                                painter = painterResource(R.drawable.profile_jyxib9mzkkwk),
                                contentDescription = "D"
                            )
                        }
                        TextButton(
                            onClick = {
                            },
                            modifier = Modifier.padding(start = 15.dp)
                        ) {
                            Text(
                                text = "Ivanox Ivan",
                                style = TextStyle(
                                    color = colorResource(id = R.color.white)
                                )
                            )
                        }
                    }

                    // Роль
                    Text(
                        "Driver",
                        modifier = Modifier.padding(start = 81.dp, top = 8.dp),
                        style = TextStyle(
                            color = colorResource(id = R.color.white)
                        )
                    )

                    // История
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigate(route = Screen.History.route)
                            },
                        ) {
                            Image(
                                painter = painterResource(R.drawable.history_eme2taqywdcb),
                                contentDescription = "D",
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        TextButton(
                            onClick = {
                                navController.navigate(route = Screen.History.route)
                            },
                            modifier = Modifier.padding(start = 15.dp)
                        ) {
                            Text("History",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }

                    // Настройки
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        IconButton(
                            onClick = { navController.navigate(route = Screen.History.route) }
                        ) {
                            Image(
                                painter = painterResource(R.drawable.setting_fbx632naln18),
                                contentDescription = "D",
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        TextButton(
                            onClick = {
                                navController.navigate(route = Screen.Setting.route)
                            },
                            modifier = Modifier.padding(start = 15.dp)
                        ) {
                            Text(
                                "Setting",
                                textAlign = TextAlign.Center,
                                color = Color.White)
                        }
                    }
                }
            }
        }
    ) {
        google(drawerState, scope, navController, requestPermissionLauncher)
    }
}