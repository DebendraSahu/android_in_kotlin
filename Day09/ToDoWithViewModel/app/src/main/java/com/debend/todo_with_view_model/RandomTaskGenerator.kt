package com.debend.todo_with_view_model

import kotlin.random.Random


object RandomTaskGenerator {
    private val titles = listOf(
        "Submit project report",
        "Prepare presentation slides",
        "Schedule team meeting",
        "Review code changes",
        "Complete UI design mockups",
        "Fix login page bug",
        "Write unit tests",
        "Update documentation",
        "Plan sprint backlog",
        "Optimize database queries"
    )

    private val descriptions = listOf(
        "Finalize and submit the monthly project report.",
        "Create engaging slides for the upcoming client meeting.",
        "Coordinate with the team and finalize a meeting schedule.",
        "Go through the latest code changes and provide feedback.",
        "Design and finalize the UI mockups for the new feature.",
        "Debug and resolve the login page issue reported by QA.",
        "Write comprehensive unit tests for the new module.",
        "Update project documentation with the latest changes.",
        "Plan and prioritize tasks for the upcoming sprint.",
        "Optimize database queries for better performance."
    )

    private fun generateRandomDeadLine(): Long {
        val currentTime = System.currentTimeMillis() // Current time in epoch milliseconds
        val randomFutureTime = Random.nextLong(1, 7) * 24 * 60 * 60 * 1000 // Random 1-7 days in ms
        return currentTime + randomFutureTime
    }

    private fun generateTitle(): String = titles.random()
    private fun generateDescription(): String = descriptions.random()

    fun generateTask() = Task(generateTitle(), generateRandomDeadLine(), generateDescription())

    fun generateMultipleTasks(count: Int = 10): List<Task> {
        return List(count) { generateTask() }
    }
}