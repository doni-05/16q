package com.example.pr17

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pr17.ui.theme.Pr17Theme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Разрешение предоставлено, можно использовать геопозицию
            Toast.makeText(this, "Разрешение предоставлено", Toast.LENGTH_SHORT).show()
        } else {
            // Разрешение не предоставлено
            Toast.makeText(this, "Разрешение не предоставлено", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pr17Theme {
                val navController = rememberNavController()
                Navigation(navController = navController, requestPermissionLauncher = requestPermissionLauncher)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun google(drawerState: DrawerState, scope: CoroutineScope, navController: NavController,requestPermissionLauncher:ActivityResultLauncher<String>) {
    val Novosibirsk = LatLng(55.041, 82.9346)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(Novosibirsk, 10f)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        val context = LocalContext.current

        val mapProperties = MapProperties(
            maxZoomPreference = 18f,
            minZoomPreference = 5f,
            isTrafficEnabled = true,
            isBuildingEnabled = true,
            isMyLocationEnabled = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
        val uiSettings = MapUiSettings(
            compassEnabled = true,
            zoomControlsEnabled = false
        )

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = uiSettings
        ) {
            // Добавьте маркеры или другие элементы на карту, если необходимо
        }

        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Запрос разрешения, если оно не предоставлено
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            // Разрешение уже предоставлено
            Toast.makeText(context, "Разрешение уже предоставлено", Toast.LENGTH_SHORT).show()
        }

        Row(
            modifier = Modifier.padding(start = 20.dp, end = 290.dp, top = 50.dp)
        ) {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.menu_nxy9sj4ru9mu),
                    contentDescription = "Меню"
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            var showBottomDrawer by remember { mutableStateOf(false) }

            IconButton(
                onClick = {
                    showBottomDrawer = !showBottomDrawer
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.group_13),
                    modifier = Modifier.size(250.dp),
                    contentDescription = "Такси"
                )
            }

            if (showBottomDrawer) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomDrawer = false },
                    sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                    containerColor = colorResource(R.color.teal_200),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(378.dp),
                        verticalArrangement = Arrangement.Bottom,
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Card(
                                modifier = Modifier
                                    .size(width = 130.dp, height = 100.dp)
                                    .padding(8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = colorResource(R.color.Кнопка), //Card background color
                                ),
                                shape = RoundedCornerShape(25.dp),
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    IconButton(
                                        onClick = {},
                                        modifier = Modifier.padding()
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.fiat1),
                                            modifier = Modifier.size(60.dp),
                                            contentDescription = "Icon 2"
                                        )
                                    }
                                    Text(
                                        "1,5 km",
                                        color = colorResource(R.color.white),
                                    )
                                }
                            }
                            Card(
                                modifier = Modifier
                                    .size(width = 130.dp, height = 100.dp)
                                    .padding(8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = colorResource(R.color.Кнопка), //Card background color
                                ),
                                shape = RoundedCornerShape(25.dp),
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    IconButton(onClick = {}, modifier = Modifier.padding()) {
                                        Icon(
                                            painter = painterResource(R.drawable.sewing),
                                            modifier = Modifier.size(60.dp),
                                            contentDescription = "Icon 2"
                                        )
                                    }
                                    Text(
                                        "1,5 km",
                                        color = colorResource(R.color.white),
                                    )
                                }
                            }
                            Card(
                                modifier = Modifier
                                    .size(width = 130.dp, height = 100.dp)
                                    .padding(8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = colorResource(R.color.Кнопка), //Card background color
                                ),
                                shape = RoundedCornerShape(25.dp),
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    IconButton(onClick = {}, modifier = Modifier.padding()) {
                                        Icon(
                                            painter = painterResource(R.drawable.group1),
                                            modifier = Modifier.size(60.dp),
                                            contentDescription = "Icon 2"
                                        )
                                    }
                                    Text(
                                        "5 min-free",
                                        color = colorResource(R.color.white),
                                    )
                                }
                            }
                        }
                        Text(
                            text = "Price:",
                            color = colorResource(R.color.white),
                            modifier = Modifier.padding(16.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Card(
                                modifier = Modifier
                                    .size(width = 130.dp, height = 100.dp)
                                    .padding(8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = colorResource(R.color.jf), //Card background color
                                ),
                                shape = RoundedCornerShape(25.dp),
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        "Minute",
                                        color = colorResource(R.color.white)
                                    )
                                    Text(
                                        "1 min - \$1",
                                        modifier = Modifier.padding(top = 9.dp),
                                        color = colorResource(R.color.white)
                                    )
                                }
                            }

                            Card(
                                modifier = Modifier
                                    .size(110.dp, 100.dp)
                                    .padding(8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = colorResource(R.color.Кнопка), //Card background color
                                ),
                                shape = RoundedCornerShape(25.dp),
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        "Hour",
                                        color = colorResource(R.color.white)
                                    )
                                    Text(
                                        "60 min - $50,",
                                        modifier = Modifier.padding(top = 9.dp),
                                        color = colorResource(R.color.white)
                                    )
                                }
                            }
                            Card(
                                modifier = Modifier
                                    .size(width = 130.dp, height = 100.dp)
                                    .padding(8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = colorResource(R.color.Кнопка), //Card background color
                                ),
                                shape = RoundedCornerShape(25.dp),
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        "Day",
                                        color = colorResource(R.color.white)
                                    )
                                    Text(
                                        "1440 min - \$300",
                                        modifier = Modifier.padding(top = 9.dp),
                                        color = colorResource(R.color.white)
                                    )
                                }
                            }
                        }
                        Button(
                            onClick = {
                                navController.navigate(Screen.WaiTimer.route)
                            },
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .height(57.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.jf)
                            )
                        ) {
                            Text(
                                text = "BOOK",
                                fontSize = 24.sp
                            )
                        }
                    }
                }
            }
        }
    }

}