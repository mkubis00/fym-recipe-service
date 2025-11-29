package com.mkvbs.recipe_service.domain

import java.util.*

class Recipe(
    val id: UUID? = null,
    var ingredients: Set<Ingredient>,
    val name: String,
    val description: String,
    val steps: Set<String>,
) {

}