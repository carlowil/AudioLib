package com.carlowil.audiolib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.carlowil.audiolib.ui.theme.AudioLibTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.carlowil.audiolib.composables.BottomMenu
import com.carlowil.audiolib.navigation.BookOverview
import com.carlowil.audiolib.navigation.Library
import com.carlowil.audiolib.navigation.MyBooks
import com.carlowil.audiolib.navigation.Profile
import com.carlowil.audiolib.navigation.audioLibBottomBarScreens
import com.carlowil.audiolib.screens.BookOverviewScreen
import com.carlowil.audiolib.screens.LibraryScreen
import com.carlowil.audiolib.screens.MyBooksScreen
import com.carlowil.audiolib.screens.ProfileScreen
import com.carlowil.audiolib.viewmodels.AudioLibViewModel

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


@Composable
fun AudioLib(){
    AudioLibTheme {
        val viewModel = viewModel<AudioLibViewModel>()
        var bottomBarState by rememberSaveable {
            mutableStateOf(true)
        }
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen = audioLibBottomBarScreens.find { it.route == currentDestination?.route } ?: Library
        bottomBarState = currentDestination?.route == currentScreen.route
        Scaffold(bottomBar = {
            if(bottomBarState) {
                BottomMenu(
                    allScreens = audioLibBottomBarScreens,
                    currentScreen = currentScreen,
                    onScreenSelected = { newScreen ->
                        navController.navigateSingleTopTo(newScreen.route)
                    })
            }
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
                    LibraryScreen(viewModel) { bookId ->
                        navController.navigate(bookId)
                    }
                }

                composable(route = Profile.route) {
                    ProfileScreen()
                }

                composable(
                    route = "${BookOverview.route}/{bookId}",
                    arguments = listOf(
                        navArgument("bookId") {type = NavType.StringType}
                    )
                ) {
                    val bookId = it.arguments?.getString("bookId")
                    bookId?.let { id -> BookOverviewScreen(viewModel = viewModel, bookId = id) }
                }
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
