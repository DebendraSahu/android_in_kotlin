package com.debend.multi_screen_navigation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.debend.multi_screen_navigation.User
import com.debend.multi_screen_navigation.UserViewModel

@Composable
fun UserFormScreen(userViewModel: UserViewModel = UserViewModel(), onUserAdded: () -> Unit = {}) {
    val slNo = (userViewModel.users.value?.size ?: 0) + 1
    var name by remember { mutableStateOf("") }
    var age by remember { mutableIntStateOf(0) }
    var contactNumber by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(top = 50.dp, start = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add user Detail",
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.headlineMedium
        )


        OutlinedTextField(value = name,
            onValueChange = { name = it },
            label = { Text(text = "Name") })
        OutlinedTextField(value = contactNumber,
            onValueChange = {
                contactNumber = it
                isError = it.length != 10 || it.any { char -> !char.isDigit() }
            },
            label = { Text(text = "Contact") },
            isError = isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(value = age.toString(),
            onValueChange = { age = if (it.isNotEmpty()) it.toInt() else 0 },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = {
                Text(text = "Age")
            })

        if (isError) {
            Text(
                text = "Invalid contact number. Please enter a 10-digit number.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Button(
            onClick = {
                userViewModel.addUser(User(slNo, name, age, contactNumber))
                onUserAdded()
            }, enabled = !isError && contactNumber.isNotEmpty()
        ) {
            Text("Submit")
        }

    }
}