package com.bisht.recipejetpackcomposeapp.presentation.ui.RecipList

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bisht.recipejetpackcomposeapp.domain.model.Recipe
import com.bisht.recipejetpackcomposeapp.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository,
    private @Named("auth_token") val token: String
): ViewModel() {

    val recipes: MutableState<List<Recipe>> = mutableStateOf(ArrayList())
    val query = mutableStateOf("")

    init {
        newSearch(query.value)
    }

    fun newSearch(query: String) {
        viewModelScope.launch {
            val result = repository.search(
                token = token,
                page = 1,
                query = query
            )
            recipes.value = result
        }
    }

    fun onQueryChanged( query: String){
        this.query.value = query
    }
}