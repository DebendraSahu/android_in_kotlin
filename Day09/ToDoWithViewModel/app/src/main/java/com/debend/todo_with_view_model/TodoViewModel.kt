package com.debend.todo_with_view_model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodoViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    private val _taskListFlow = MutableStateFlow(taskRepository.getTasks())
    val taskListFlow: StateFlow<List<Task>> = _taskListFlow.asStateFlow()

    fun addTask(task: Task) {
        taskRepository.addTask(task)
        _taskListFlow.value = taskRepository.getTasks().toList()
    }

    fun removeTask(task: Task) {
        taskRepository.removeTask(task)
        _taskListFlow.value = taskRepository.getTasks().toList()
    }

    fun modifyTask(oldTask: Task, newTask: Task) {
        taskRepository.modifyTask(oldTask, newTask)
        _taskListFlow.value = taskRepository.getTasks().toList()
    }
}
