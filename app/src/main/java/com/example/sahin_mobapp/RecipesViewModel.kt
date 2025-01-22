package com.example.sahin_mobapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RecipesViewModel : ViewModel() {

    private val _recipeList = MutableStateFlow<List<Recipe>>(emptyList())
    val recipeList: StateFlow<List<Recipe>> = _recipeList

    private val allRecipes = listOf(
        Recipe(1, "Tonkotsu Ramen", "Is a Japanese noodle soup dish that originated in Fukuoka, Fu..", R.drawable.ramen),
        Recipe(2, "Takoyaki", "Is a Japanese snack that originated in Osaka, Japan. It is a ball-shaped cake made fr..", R.drawable.takoyaki),
        Recipe(3, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare..", R.drawable.udon)
    )

    fun updateQuery(query: String) {
        if (query.length < 3) {
            _recipeList.value = allRecipes
        } else {
            val filteredList = allRecipes.filter {
                it.name.contains(query, ignoreCase = true) || it.details.contains(query, ignoreCase = true)
            }
            _recipeList.update { filteredList }
        }
    }

    fun loadAllRecipes() {
        _recipeList.value = allRecipes
    }
}
