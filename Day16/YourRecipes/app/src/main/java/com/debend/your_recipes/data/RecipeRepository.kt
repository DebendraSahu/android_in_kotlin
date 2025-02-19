package com.debend.your_recipes.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val recipeDao: RecipeDao) {
    val recipes: Flow<List<Recipe>> = recipeDao.getAllRecipes()

    suspend fun addRecipe(recipe: Recipe) {
        recipeDao.insertRecipe(recipe)
    }

    suspend fun updateRecipe(recipe: Recipe) {
        recipeDao.updateRecipe(recipe)
    }

    suspend fun removeRecipe(recipe: Recipe) {
        recipeDao.deleteRecipe(recipe)
    }
}
