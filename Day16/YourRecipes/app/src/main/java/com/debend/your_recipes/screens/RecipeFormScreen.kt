package com.debend.your_recipes.screens;


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Modifier
import com.debend.your_recipes.view_models.RecipeViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.debend.your_recipes.data.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RecipeForm(
    recipeViewModel: RecipeViewModel = hiltViewModel(), onRecipeAdded: () -> Unit = {}
) {
    // Collect the list of recipes to determine the next id (or use auto-generation in your entity)
    val recipes by recipeViewModel.recipes.collectAsState(initial = emptyList())
    // Calculate newId based on the current recipes (or remove this if you use auto-generated IDs)
    val newId = (recipes.maxOfOrNull { it.id } ?: 0L) + 1L

    // Form fields as state variables
    var title by remember { mutableStateOf("") }
    var cookingTime by remember { mutableStateOf("") } // use String to allow user input
    var description by remember { mutableStateOf("") }
    var ingredients by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar(modifier = Modifier.background(MaterialTheme.colorScheme.primary),
            title = { Text("Add New Recipe", color = MaterialTheme.colorScheme.onPrimary) })
    }) { innerPadding ->
        // Make the form scrollable
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Enter Recipe Details", style = MaterialTheme.typography.headlineSmall
            )

            OutlinedTextField(value = title,
                onValueChange = { title = it },
                label = { Text("Dish Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(value = cookingTime,
                onValueChange = {
                    // Allow only digits
                    cookingTime = it.filter { char -> char.isDigit() }
                },
                label = { Text("Cooking Time (min)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                maxLines = 5
            )

            OutlinedTextField(value = ingredients,
                onValueChange = { ingredients = it },
                label = { Text("Ingredients") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(value = category,
                onValueChange = { category = it },
                label = { Text("Category") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    // Convert cookingTime to an integer, defaulting to 0 if conversion fails
                    val time = cookingTime.toIntOrNull() ?: 0
                    val newRecipe = Recipe(
                        id = newId,
                        title = title,
                        description = description,
                        ingredients = ingredients,
                        cookingTime = time,
                        category = category
                    )
                    recipeViewModel.addRecipe(newRecipe)
                    onRecipeAdded()
                },
                enabled = title.isNotBlank() && description.isNotBlank() && ingredients.isNotBlank(),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Submit")
            }
        }
    }
}
