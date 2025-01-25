package com.debend.compose_navigation_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home_screen") {
        composable("home_screen") {
            HomeScreen(navController)
        }
        composable("list_screen") {
            ListScreen(navController)
        }
        composable("detail_screen/{item}") { backStackEntry ->
            val item = backStackEntry.arguments?.getString("item")
            DetailScreen(item)
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = { navController.navigate("list_screen") }) {
            Text(text = "Navigate to list")
        }
    }
}

@Composable
fun ListScreen(navController: NavHostController) {
    val items = (0..100).map { "Item $it" }

    LazyColumn(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items) { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clickable {
                        navController.navigate("detail_screen/$item")
                    }, contentAlignment = Alignment.Center
            ) {
                BasicText(item)
            }
        }
    }
}

@Composable
fun DetailScreen(item: String?) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Details of $item", fontSize = 30.sp)
    }
}
