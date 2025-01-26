package com.debend.multi_screen_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.debend.multi_screen_navigation.screens.HomeScreen
import com.debend.multi_screen_navigation.screens.UserDetailScreen
import com.debend.multi_screen_navigation.screens.UserFormScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val userViewModel = UserViewModel()

    var userSelected by remember {
        mutableStateOf<User?>(null)
    }

    NavHost(navController = navController, startDestination = "home_screen") {
        composable("home_screen") {
            HomeScreen(userViewModel, {
                navController.navigate("form_screen")
            }, {
                userSelected = it
                navController.navigate("detail_screen")
            })
        }
        composable("form_screen") {
            UserFormScreen(userViewModel) {
                navController.navigate("home_screen")
            }
        }
        composable("detail_screen") {
            UserDetailScreen(userSelected)
        }
    }
}

//@Preview
@Composable
fun PreviewHomeScreen() {
    val userViewModel = UserViewModel()
    userViewModel.addUser(User(1, "Dev", 25, "9876543210"))
    HomeScreen(userViewModel, {}, {})
}


@Preview
@Composable
fun PreviewDetailScreen() {
    val user = User(1, "Dev", 25, "9876543210")
    UserDetailScreen(user)
}

