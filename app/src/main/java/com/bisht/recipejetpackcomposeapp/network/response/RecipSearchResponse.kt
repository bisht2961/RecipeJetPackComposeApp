package com.bisht.recipejetpackcomposeapp.network.response

import com.bisht.recipejetpackcomposeapp.network.model.RecipeDto
import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse (

    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDto>,

    )