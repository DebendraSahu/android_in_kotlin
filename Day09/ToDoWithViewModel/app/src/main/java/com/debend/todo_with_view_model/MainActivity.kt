package com.debend.todo_with_view_model

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.debend.todo_with_view_model.ui.theme.ToDoWithViewModelTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    private val todoViewModel: TodoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoWithViewModelTheme {
                HomeScreen(todoViewModel)
            }
        }
    }
}

@Composable
fun HomeScreen(todoViewModel: TodoViewModel) {
    val taskList by todoViewModel.taskListFlow.collectAsState(emptyList())

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = AbsoluteAlignment.Left
    ) {
        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "Tasks List", style = MaterialTheme.typography.headlineLarge)
        TaskListUI(taskList)
        Button(onClick = { todoViewModel.addTask(RandomTaskGenerator.generateTask()) }) {
            Text("Add Task")
        }
        Button(onClick = { if (taskList.isNotEmpty()) todoViewModel.removeTask(taskList[0]) }) {
            Text("Remove Task")
        }
    }
}

@Preview
@Composable
fun TaskCard(
    task: Task = RandomTaskGenerator.generateTask(), onItemPressed: (Task) -> Unit = {}
) {
    Card(shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemPressed(task) }) {
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
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp
                )
                Text(
                    text = "DeadLine: ${formatTime(task.deadline)}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview
@Composable
fun TaskListUI(
    taskList: List<Task> = RandomTaskGenerator.generateMultipleTasks(),
    onItemPressed: (Task) -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxHeight(0.7F)
            .fillMaxWidth(1.0F)
    ) {
        items(taskList, key = { it.taskId }) { task ->
            TaskCard(task = task, onItemPressed)
        }
    }
}

// Convert epoch time (milliseconds) to a readable date string
fun formatTime(epochTime: Long): String {
    val date = Date(epochTime)
    val format = SimpleDateFormat("hh:mm, dd MMM yyyy", Locale.getDefault()) // Updated format
    return format.format(date)
}
