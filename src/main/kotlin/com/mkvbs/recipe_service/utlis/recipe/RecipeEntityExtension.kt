package com.mkvbs.recipe_service.utlis.recipe

import com.mkvbs.recipe_service.domain.Recipe
import com.mkvbs.recipe_service.entity.RecipeEntity
import com.mkvbs.recipe_service.utlis.ingredient.toDomain

fun RecipeEntity.toDomain(): Recipe {
    return Recipe(id, ingredients.map { it.toDomain() }.toSet(), name, description, steps)
}