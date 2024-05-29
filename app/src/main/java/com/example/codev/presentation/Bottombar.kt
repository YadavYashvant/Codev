package com.example.codev.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavController
import com.example.codev.R


data class BottomNavigationItem(
    val title: String,
    val selectedIcon: Painter,
    val unselectedIcon: Painter,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

val spacefamily = FontFamily(
    Font(R.font.spacebold/*, FontWeight.Bold*/),
    Font(R.font.spaceregular/*, FontWeight.Light*/),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigation(
navController: NavController
){
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = painterResource(id = R.drawable.home_filled),
            unselectedIcon = painterResource(id = R.drawable.home_outlined),
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Projects",
            selectedIcon = painterResource(id = R.drawable.baseline_menu_book_24),
            unselectedIcon = painterResource(id = R.drawable.outline_library_books_24),
            hasNews = false,
            /*badgeCount = 0*/
        ),
        BottomNavigationItem(
            title = "Bookmarks",
            selectedIcon = painterResource(id = R.drawable.bookmark_filled),
            unselectedIcon = painterResource(id = R.drawable.bookmark_outlined),
            hasNews = true,
        ),
        BottomNavigationItem(
            title = "Settings",
            selectedIcon = painterResource(id = R.drawable.baseline_settings_24),
            unselectedIcon = painterResource(id = R.drawable.outline_settings_24),
            hasNews = false,
        )
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.title)
                    // navController.navigate(item.title)
                },
                label = {
                    Text(text = item.title)
                },
                alwaysShowLabel = false,
                icon = {
                        Icon(
                            painter = if (index == selectedItemIndex) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = item.title
                        )
                })
        }
    }
}