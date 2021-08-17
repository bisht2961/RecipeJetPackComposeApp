package com.bisht.recipejetpackcomposeapp.presentation.ui.RecipList

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bisht.recipejetpackcomposeapp.domain.model.Recipe
import com.bisht.recipejetpackcomposeapp.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)
    var categoryScrollPosition: Int = 0
    val loading = mutableStateOf(false)

    init {
        newSearch()
    }

    fun newSearch() {
        viewModelScope.launch {
            loading.value = true
            resetSearchState()
//            delay(3000)
            val result = repository.search(
                token = token,
                page = 1,
                query = query.value
            )
            loading.value = false
            recipes.value = result
        }
    }

    private fun resetSearchState(){
        recipes.value = listOf()
        if(selectedCategory.value?.value != query.value){
            clearSelectedCategory()
        }
    }

    private fun clearSelectedCategory(){
        selectedCategory.value = null
    }

    fun onQueryChanged( query: String){
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String){
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChanged(category)
    }

    fun onChangeCategoryScrollPosition( position: Float ){
        categoryScrollPosition = position.toInt()

    }

}
