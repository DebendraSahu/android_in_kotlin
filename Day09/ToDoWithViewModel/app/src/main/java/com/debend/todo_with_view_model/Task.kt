package com.debend.todo_with_view_model

import java.util.UUID

data class Task(
    val title: String,
    val deadline: Long,
    val description: String,
    val taskId: String = UUID.randomUUID().toString()
)
// later list of sub-tasks can be added