package com.debend.your_recipes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val ingredients: String,
    val cookingTime: Int, // in minutes
    val category: String
)
