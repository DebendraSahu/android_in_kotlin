package com.debend.todo_with_view_model


class TaskRepository {
    private var taskList: List<Task> = RandomTaskGenerator.generateMultipleTasks()

    // No need to expose MutableList directly
    fun addTask(task: Task) {
        taskList = taskList + task
    }

    fun removeTask(task: Task) {
        taskList = taskList - task
    }

    fun getTasks(): List<Task> { // Return an immutable list
        return taskList
    }

    fun modifyTask(oldTask: Task, newTask: Task): Boolean {
        val currentList = taskList
        val index = currentList.indexOf(oldTask)
        if (index != -1) {
            val updatedList = currentList.toMutableList().apply { this[index] = newTask }
            taskList = updatedList
            return true
        }
        return false
    }

}
