package com.carlowil.audiolib.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.carlowil.audiolib.navigation.AudioLibDestination

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