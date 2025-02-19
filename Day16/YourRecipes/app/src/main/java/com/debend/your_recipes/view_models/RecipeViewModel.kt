package com.debend.your_recipes.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.debend.your_recipes.data.Recipe
import com.debend.your_recipes.data.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    val recipes: StateFlow<List<Recipe>> =
        repository.recipes.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            repository.addRecipe(recipe)
        }
    }
}
