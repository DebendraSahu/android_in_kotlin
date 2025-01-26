package com.debend.multi_screen_navigation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.debend.multi_screen_navigation.User


@Composable
fun UserDetailScreen(user: User?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        if (user == null) {
            Text(
                text = "Details are empty",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Details of ${user.name}",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                HorizontalDivider(
                    modifier = Modifier.padding(end = 32.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                )
                Text(
                    text = "SL: ${user.slNo}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 18.sp
                )
                Text(
                    text = "Age: ${user.age}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 18.sp
                )
                Text(
                    text = "Phone: ${user.contact}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 18.sp
                )
            }
        }
    }
}