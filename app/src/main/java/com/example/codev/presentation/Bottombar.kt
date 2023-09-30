import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavController
import com.example.codev.R


data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
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
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Projects",
            selectedIcon = Icons.Filled.DateRange,
            unselectedIcon = Icons.Outlined.DateRange,
            hasNews = false,
            /*badgeCount = 0*/
        ),
        BottomNavigationItem(
            title = "Notifications",
            selectedIcon = Icons.Filled.Notifications,
            unselectedIcon = Icons.Outlined.Notifications,
            hasNews = true,
        ),
        BottomNavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
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
                    BadgedBox(
                        badge = {
                            if (item.badgeCount != null) {
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            } else if (item.hasNews) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (index == selectedItemIndex) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = item.title
                        )
                    }
                })
        }
    }
}
















/*
package com.example.codev.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomNavigation() {
    BottomAppBar(
        modifier = Modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp),
            verticalAlignment = Alignment.Bottom
        ) {

            IconButton(
                onClick = {
                },
                modifier = Modifier
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        Icons.Outlined.Home,
                        contentDescription = "description",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(text = "Home", fontSize = 12.sp, modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            IconButton(
                onClick = { */
/*TODO*//*
 },
                modifier = Modifier,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        Icons.Outlined.Settings,
                        contentDescription = "description",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(text = "Settings", fontSize = 10.sp, modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            IconButton(
                onClick = { */
/*TODO*//*
 },
                modifier = Modifier
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        Icons.Outlined.Create,
                        contentDescription = "description",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(text = "Create", fontSize = 12.sp, modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            ExtendedFloatingActionButton(
                onClick = { */
/*TODO*//*
 },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "checking"
                )
            }
        }
    }
}*/
