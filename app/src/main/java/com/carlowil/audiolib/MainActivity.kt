package com.carlowil.audiolib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.carlowil.audiolib.ui.theme.AudioLibTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.toUpperCase
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudioLibTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AudioLib()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AudioLib(){
    AudioLibTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen = audioLibBottomBarScreens.find { it.route == currentDestination?.route } ?: Library
        Scaffold(bottomBar = {
            BottomMenu(
                allScreens = audioLibBottomBarScreens,
                currentScreen = currentScreen,
                onScreenSelected = { newScreen ->
                    navController.navigateSingleTopTo(newScreen.route)
                })
        }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Library.route,
                modifier = Modifier.padding(innerPadding)) {
                composable(route = MyBooks.route) {
                    MyBooksScreen()
                }

                composable(route = Library.route) {
                    LibraryScreen()
                }

                composable(route = Profile.route) {
                    ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun BottomMenu(
    allScreens : List<AudioLibDestination>,
    currentScreen : AudioLibDestination,
    onScreenSelected : (AudioLibDestination) -> Unit
) {
    Surface(Modifier.fillMaxWidth()) {
        NavigationBar {
            allScreens.forEach { screen ->
                NavigationBarItem(
                    icon = { Icon(imageVector = screen.icon, contentDescription = screen.route) },
                    label = { Text(screen.route.capitalize()) },
                    selected = currentScreen == screen,
                    onClick = { onScreenSelected(screen) }
                )
            }
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) {
    this.navigate(route) {
        // В случае нажатия стрелки назад из любого пункта назначения
        // попадем обратно в начальный Compose элемент
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Сохранит данные при повторном нажатие на ту же вкладку
        restoreState = true
        // Гарантирует, что будет только одна копия в бэкстеке
        launchSingleTop = true
    }
}

@Composable
fun LibraryScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Text(text = "This is LibraryScreen")
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Text(text = "This is ProfileScreen")
    }
}

@Composable
fun MyBooksScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Text(text = "This is MyBooksScreen")
    }
}