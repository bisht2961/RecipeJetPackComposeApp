package com.bisht.recipejetpackcomposeapp.presentation.ui.RecipList

enum class FoodCategory (val value: String){
    CHICKEN("chicken"),
    BEEF("beef"),
    SOUP("soup"),
    DESSERT("dessert"),
    VEGETARIAN("vegetarian"),
    MILK("milk"),
    VEGAN("vegan"),
    PIZZA("pizza"),
    DONUT("donut"),
}

fun getAllFoodCategories(): List<FoodCategory>{
    return listOf(
        FoodCategory.BEEF,
        FoodCategory.CHICKEN,
        FoodCategory.SOUP,
        FoodCategory.DESSERT,
        FoodCategory.VEGETARIAN,
        FoodCategory.MILK,
        FoodCategory.VEGAN,
        FoodCategory.PIZZA,
        FoodCategory.DONUT,
    )
}
fun getFoodCategory(value: String):FoodCategory?{
    val map = FoodCategory.values().associateBy( FoodCategory::value )
    return map[value]
}
const val CATEGORYSIZE = 9