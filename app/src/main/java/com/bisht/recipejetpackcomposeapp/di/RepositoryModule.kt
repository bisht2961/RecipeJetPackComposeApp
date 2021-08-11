package com.bisht.recipejetpackcomposeapp.di

import com.bisht.recipejetpackcomposeapp.network.RecipeService
import com.bisht.recipejetpackcomposeapp.network.model.RecipeDtoMapper
import com.bisht.recipejetpackcomposeapp.repository.RecipeRepository
import com.bisht.recipejetpackcomposeapp.repository.RecipeRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeDtoMapper: RecipeDtoMapper
    ):RecipeRepository{
        return RecipeRepositoryImplementation(recipeService, recipeDtoMapper)
    }
}