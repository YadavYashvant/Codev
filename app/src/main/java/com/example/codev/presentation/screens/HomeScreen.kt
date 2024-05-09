package com.example.codev.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.codev.animations.AnimatedPreloaderBill
import com.example.codev.components.CircularProgressBar
import com.example.codev.components.PostCard
import com.example.codev.components.SearchBarHome
import com.example.codev.data.DataOrException
import com.example.codev.firestore_feature.PostsViewModel
import com.example.codev.firestore_feature.model.Post
import com.example.codev.presentation.FilterChipHome
import com.example.codev.presentation.ProfileDialog
import com.example.codev.presentation.sign_in.GoogleAuthUiClient
import com.example.codev.presentation.sign_in.UserData
import com.example.codev.ui.theme.greenV
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    navController_par: NavHostController,
    userData: UserData?,
    googleAuthUiClient: GoogleAuthUiClient,
    postsviewModel: PostsViewModel,
    dataOrException: DataOrException<List<Post>, Exception>
) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    val items = listOf( Icons.Default.Home, Icons.Filled.Settings, Icons.Filled.Search, Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email,)
    val items_name = listOf("Home", "Settings", "Search","Favorite", "Profile", "Contact", )
    val selectedItem = remember{
        mutableStateOf(items[0])
    }
    BackHandler(enabled = drawerState.isOpen) {
        scope.launch {
            drawerState.close()
        }
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary),

        drawerContent = {
            DismissibleDrawerSheet{
                Spacer(Modifier.height(12.dp))
                items.forEachIndexed {index,item->
                    NavigationDrawerItem(
                        icon = { Icon(item, contentDescription = null)},
                        label = { Text(items_name[index]) },
                        selected = item == selectedItem.value ,
                        onClick = { /*TODO*/
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                        },
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        shape = RoundedCornerShape(20.dp)
                    )
                }
            }
        }
    ) {

        val posts = dataOrException.data
//    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
//            .nestedScroll(scrollBehavior.nestedScrollConnection),
            ,
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            "Codev",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 25.sp
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    },

                    actions = {
                        if (userData != null) {
                            ProfileDialog(userData = userData, googleAuthUiClient = googleAuthUiClient, navController = navController_par)
                        }
                    },
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp)
                /*.padding(bottom = 66.dp, top = 66.dp)*/

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                    /*.verticalScroll(scrollState)*/
                ) {
                    var value by remember { mutableStateOf("") }
                    val onValueChange: (String) -> Unit = {
                        postsviewModel.searchPosts(value)
                        Log.d("HomeScreen", "searching for $value")
                    }

                    posts?.let {
                        LazyColumn {
                            item {

                                AnimatedPreloaderBill(
                                    modifier = Modifier
                                        .height(300.dp)
                                        .fillMaxWidth()
                                    //.padding(top = 40.dp)
                                )

                                /*TextField(
                                    value = value,
                                    onValueChange = onValueChange,
                                    shape = MaterialTheme.shapes.extraLarge,
                                    placeholder = {
                                        Text(
                                            text = "Search Projects",
                                            fontFamily = com.example.codev.spacefamily,
                                            textAlign = TextAlign.Center,
                                        )
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 30.dp),
                                    colors = TextFieldDefaults.textFieldColors(
                                        unfocusedIndicatorColor = Color.Transparent,
                                        focusedIndicatorColor = Color.Transparent
                                    ),
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Outlined.Search,
                                            contentDescription = null,
                                            Modifier
                                                .scale(1.3F)
                                                .padding(10.dp)
                                        )
                                    },
                                )*/

                                Spacer(modifier = Modifier.height(20.dp))

                                val chipList = listOf(
                                    "Latest",
                                    "Popular",
                                    "Top Rated",
                                    "Upcoming",
                                    "Now Playing",
                                    "Trending"
                                )

                                val scrollstate = rememberScrollState()
                                Row(
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                        .paddingFromBaseline(bottom = 16.dp, top = 32.dp)
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .horizontalScroll(scrollstate)
                                ) {
                                    for (i in 0..5) {
                                        FilterChipHome(chipList[i])
                                    }
                                }
                            }

                            items(
                                items = posts
                            ) { post ->

                                PostCard(post = post)

                            }
                        }
                    }

                    val e = dataOrException.e
                    e?.let {
                        Text(
                            text = e.message!!,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressBar(isDisplayed = postsviewModel.loading.value)

                    }
                }
                FloatingActionButton(
                    onClick = {
                        navController.navigate("addproject")
                    },
                    containerColor = greenV,
                    modifier = Modifier
                        .padding(bottom = 20.dp, end = 30.dp)
                        .align(Alignment.BottomEnd)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add, contentDescription = "add project",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}



