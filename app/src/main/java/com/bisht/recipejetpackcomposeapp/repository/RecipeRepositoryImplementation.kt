package com.bisht.recipejetpackcomposeapp.repository

import com.bisht.recipejetpackcomposeapp.domain.model.Recipe
import com.bisht.recipejetpackcomposeapp.network.RecipeService
import com.bisht.recipejetpackcomposeapp.network.model.RecipeDtoMapper

class RecipeRepositoryImplementation(
    private val recipeService: RecipeService,
    private val mapper: RecipeDtoMapper
): RecipeRepository {

    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        return mapper.ToDomainList(recipeService.search(token ,page, query).recipes)
    }

    override suspend fun get(token: String, id: Int): Recipe {
        return mapper.mapToDomainModel(recipeService.get(token,id))
    }
}