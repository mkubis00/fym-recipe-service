package com.mkvbs.recipe_service.domain

import java.util.UUID

class Recipe(
    val id: UUID? = null,
    var ingredients: MutableList<Ingredient>,
    val name: String,
    val description: String,
    val steps: List<String>,
    val numberOfIngredients: Int? = ingredients.size,
) {

}