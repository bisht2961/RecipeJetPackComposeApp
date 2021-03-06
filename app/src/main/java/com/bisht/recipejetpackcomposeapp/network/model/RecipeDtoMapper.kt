package com.bisht.recipejetpackcomposeapp.network.model

import com.bisht.recipejetpackcomposeapp.domain.model.Recipe
import com.bisht.recipejetpackcomposeapp.domain.util.DomainMapper


class RecipeDtoMapper: DomainMapper<RecipeDto, Recipe> {

    override fun mapToDomainModel(model: RecipeDto): Recipe {
        return Recipe(
            id = model.pk,
            title = model.title,
            featuredImage =  model.featuredImage,
            rating = model.rating,
            publisher = model.publisher,
            sourceUrl = model.sourceUrl,
            description = model.description,
            cookingInstruction = model.cookingInstruction,
            ingredients = model.ingredients?: listOf(),
            dateAdded = model.dateAdded,
            dateUpdated = model.dateUpdated
        )
    }

    override fun mapFromDomainModel(domainModel: Recipe): RecipeDto {
        return RecipeDto(
            pk = domainModel.id,
            title = domainModel.title,
            featuredImage =  domainModel.featuredImage,
            rating = domainModel.rating,
            publisher = domainModel.publisher,
            sourceUrl = domainModel.sourceUrl,
            description = domainModel.description,
            cookingInstruction = domainModel.cookingInstruction,
            ingredients = domainModel.ingredients?: listOf(),
            dateAdded = domainModel.dateAdded,
            dateUpdated = domainModel.dateUpdated
        )
    }

    fun ToDomainList(initial: List<RecipeDto>): List<Recipe>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial:List<Recipe>):List<RecipeDto>{
        return initial.map { mapFromDomainModel(it) }
    }

}