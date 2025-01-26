package com.debend.multi_screen_navigation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.debend.multi_screen_navigation.User
import com.debend.multi_screen_navigation.UserViewModel

@Preview
@Composable
fun HomeScreen(
    userViewModel: UserViewModel = UserViewModel(),
    onAddButtonPressed: () -> Unit = {},
    onItemPressed: (user: User) -> Unit = {}
) {
    val userList by userViewModel.users.observeAsState(emptyList())

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = AbsoluteAlignment.Left
    ) {
        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "Users List", style = MaterialTheme.typography.headlineLarge)
        Button(onClick = onAddButtonPressed) { Text("Add User") }
        UserListUI(userList, onItemPressed)
    }

}

@Composable
fun UserCard(user: User, onItemPressed: (User) -> Unit) {
    Card(shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemPressed(user) }) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Age: ${user.age}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp
                )
                Text(
                    text = "Contact: ${user.contact}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun UserListUI(userList: List<User>, onItemPressed: (User) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(userList) { user -> UserCard(user = user, onItemPressed) }
    }
}
