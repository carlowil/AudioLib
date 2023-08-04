package com.carlowil.audiolib.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.ui.graphics.vector.ImageVector

interface AudioLibDestination {
    val icon : ImageVector
    val route : String
}

object Library : AudioLibDestination {
    override val icon: ImageVector = Icons.Filled.LibraryBooks
    override val route: String = "library"
}

object Profile : AudioLibDestination {
    override val icon: ImageVector = Icons.Filled.AccountCircle
    override val route: String = "profile"
}

object MyBooks : AudioLibDestination {
    override val icon: ImageVector = Icons.Filled.Book
    override val route: String = "mybooks"
}
// Поставлю любую иконку, эта информация нам не потребуется (потом нужно будет рефакторить)
object BookOverview : AudioLibDestination {
    override val icon: ImageVector = Icons.Filled.Book
    override val route: String = "bookoverview"
}

val audioLibBottomBarScreens = listOf(MyBooks, Library, Profile)