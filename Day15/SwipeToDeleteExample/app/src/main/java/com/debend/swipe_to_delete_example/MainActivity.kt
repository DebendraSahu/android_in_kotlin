package com.debend.swipe_to_delete_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Alignment
import com.debend.swipe_to_delete_example.ui.theme.SwipeToDeleteExampleTheme
import kotlinx.coroutines.launch

data class User(val id: Int, val name: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwipeToDeleteExampleTheme {
                SwipeToDeleteApp()
            }
        }
    }
}

@Composable
fun SwipeToDeleteApp() {
    val userList = remember {
        mutableStateListOf(
            User(1, "Alice"), User(2, "Bob"), User(3, "Charlie"), User(4, "David"), User(5, "Eva")
        )
    }
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(userList, key = { it.id }) { user ->
                SwipeToDeleteItem(user = user, onDelete = {
                    userList.remove(user)
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar("${user.name} deleted")
                    }
                })
            }
        }
        SnackbarHost(
            hostState = snackBarHostState, modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDeleteItem(user: User, onDelete: () -> Unit) {
    val dismissState = rememberSwipeToDismissBoxState(confirmValueChange = {
        if (it == SwipeToDismissBoxValue.EndToStart) {
            onDelete()
        }
        true
    })

    SwipeToDismiss(state = dismissState, background = {
        // Only show the background when the item is swiped
        if (dismissState.currentValue != SwipeToDismissBoxValue.Settled) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Delete",
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.CenterEnd),
                    fontSize = 18.sp
                )
            }
        }
    }, dismissContent = {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Text(
                text = user.name, modifier = Modifier.padding(16.dp), fontSize = 20.sp
            )
        }
    })
}
