package com.debend.your_recipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.debend.your_recipes.data.Recipe
import com.debend.your_recipes.screens.RecipeDetailScreen
import com.debend.your_recipes.screens.RecipeForm
import com.debend.your_recipes.screens.RecipeScreen
import com.debend.your_recipes.ui.theme.YourRecipesTheme
import com.debend.your_recipes.view_models.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YourRecipesTheme {
                val viewModel: RecipeViewModel = RecipeViewModel(
                    AppModule.provideRecipeRepository(
                        AppModule.provideRecipeDao(AppModule.provideDatabase(applicationContext))
                    )
                )
                // Create a NavController for navigation
                val navController = rememberNavController()
                NavHost(
                    navController = navController, startDestination = "recipeList"
                ) {
                    // Main recipe list screen
                    composable("recipeList") {
                        RecipeScreen(viewModel,
                            onAddRecipe = { navController.navigate("recipeForm") },
                            onRecipeClick = { recipe ->
                                navController.navigate("recipeDetail/${recipe.id}")
                            })
                    }
                    // Recipe form screen
                    composable("recipeForm") {
                        RecipeForm(viewModel, onRecipeAdded = { navController.popBackStack() })
                    }
                    // Recipe detail screen
                    composable(
                        "recipeDetail/{recipeId}",
                        arguments = listOf(navArgument("recipeId") { type = NavType.LongType })
                    ) { backStackEntry ->
                        val recipeId = backStackEntry.arguments?.getLong("recipeId") ?: 0L

                        val recipe = viewModel.recipes.value.firstOrNull { it.id == recipeId }
                        if (recipe != null) {
                            RecipeDetailScreen(recipe = recipe,
                                onBackPressed = { navController.popBackStack() })
                        } else {
                            // If recipe is not found, navigate back
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}
