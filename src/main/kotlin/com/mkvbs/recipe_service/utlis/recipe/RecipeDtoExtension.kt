package com.mkvbs.recipe_service.utlis.recipe

import com.mkvbs.recipe_service.domain.Recipe
import com.mkvbs.recipe_service.dto.recipe.RecipeDto
import com.mkvbs.recipe_service.exception.id_null.RecipeIdNullException

fun RecipeDto.toDomain(): Recipe {
    return Recipe(null, mutableListOf(), name, description, steps)
}

fun RecipeDto.toDomainWithId(): Recipe {
    if(id == null) throw RecipeIdNullException()
    return Recipe(id, mutableListOf(), name, description, steps)
}