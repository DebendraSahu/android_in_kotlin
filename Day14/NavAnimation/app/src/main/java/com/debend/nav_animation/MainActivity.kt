package com.debend.nav_animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.AnimatedNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationAnimationExample()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationAnimationExample() {
    val navController = rememberNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = "home_screen",
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = "home_screen", enterTransition = {
            fadeIn(animationSpec = tween(300)) + slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        }
            , exitTransition = {
            fadeOut(animationSpec = tween(300)) + slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        }) {
            HomeScreen(navController)
        }
        composable(route = "detail_screen", enterTransition = {
            fadeIn(animationSpec = tween(300)) + slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
            , exitTransition = {
            fadeOut(animationSpec = tween(300)) + slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }) {
            DetailScreen(navController)
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("detail_screen") }) {
            Text("Go to Detail Screen")
        }
    }
}

@Composable
fun DetailScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Detail Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Go back to Home Screen")
        }
    }
}