package com.example.a22

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a22.ui.theme.Пр22Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Пр22Theme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Пр22Theme {
        Greeting("Android")
    }
}
@Composable
fun GameBoard(rows: Int, cols: Int, images: List<Int>) {
    LazyVerticalGrid(cells = GridCells.Fixed(cols), modifier = Modifier.fillMaxSize()) {
        items(rows * cols) { index ->
            ImageCard(image = images[index])
        }
    }
}
@Composable
fun ImageCard(image: Int) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { /* Обработка нажатия */ },
        elevation = 4.dp
    ) { // Закрываем фигурную скобку
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

}
@Composable
fun GameBoard(rows: Int, cols: Int, images: List<Int>) {
    val cellStates = remember { mutableStateListOf<CellState>() }
    // Инициализация состояний ячеек
    for (i in 0 until rows * cols) {
        cellStates.add(CellState.CLOSED)
    }

    LazyVerticalGrid(
        cells = GridCells.Fixed(cols),
        modifier = Modifier.fillMaxSize()
    ) {
        items(rows * cols) { index ->
            ImageCard(
                image = if (cellStates[index] == CellState.OPEN) images[index] else R.drawable.closed,
                onClick = {
                    // Обработка нажатия
                    if (cellStates[index] == CellState.CLOSED) {
                        cellStates[index] = CellState.OPEN
                        checkMatches(cellStates, images)
                    }
                }
            )
        }
    }
}
fun checkMatches(cellStates: MutableList<CellState>, images: List<Int>) {
    val openIndices = cellStates.mapIndexedNotNull { index, state ->
        if (state == CellState.OPEN) index else null
    }

    if (openIndices.size == 2) {
        if (images[openIndices[0]] == images[openIndices[1]]) {
            // Удаляем совпавшие картинки
            cellStates[openIndices[0]] = CellState.DELETED
            cellStates[openIndices[1]] = CellState.DELETED
        } else {
            // Закрываем несовпавшие картинки
            cellStates[openIndices[0]] = CellState.CLOSED
            cellStates[openIndices[1]] = CellState.CLOSED
        }
    }
}
@Composable
fun LeaderboardScreen(scores: List<String>) {
    LazyColumn {
        items(scores) { score ->
            Text(text = score, modifier = Modifier.padding(8.dp))
        }
    }
}
@Composable
fun StartScreen(
    onStartGame: () -> Unit,
    onOpenSettings: () -> Unit,
    onOpenLeaderboard: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onStartGame) {
            Text("Начать игру")
        }
        Button(onClick = onOpenSettings) {
            Text("Настройки")
        }
        Button(onClick = onOpenLeaderboard) {
            Text("Таблица рекордов")
        }
    }
}
@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf(Screen.START) }
    val images = remember { List(18) { it % 9 } } // Пример списка изображений

    when (currentScreen) {
        Screen.START -> StartScreen(
            onStartGame = { currentScreen = Screen.GAME },
            onOpenSettings = { currentScreen = Screen.SETTINGS },
            onOpenLeaderboard = { currentScreen = Screen.LEADERBOARD }
        )
        Screen.GAME -> GameBoard(rows = 6, cols = 6, images = images)
        Screen.SETTINGS -> SettingsScreen { /* Обработка изменений настроек */ }
        Screen.LEADERBOARD -> LeaderboardScreen(scores = listOf("Игрок 1: 100", "Игрок 2: 80"))
    }
}

enum class CellState {
    OPEN, CLOSED, DELETED
}




