package com.bisht.recipejetpackcomposeapp.presentation.ui.RecipList

import androidx.lifecycle.ViewModel
import com.bisht.recipejetpackcomposeapp.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository,
    private @Named("auth_token") val token: String
): ViewModel() {

    fun getRepo() = repository

    fun getToken() = token

}