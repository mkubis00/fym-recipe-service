package com.mkvbs.recipe_service.utlis.recipe

import com.mkvbs.recipe_service.domain.Recipe
import com.mkvbs.recipe_service.dto.recipe.RecipeResponseDto

import com.mkvbs.recipe_service.entity.RecipeEntity
import com.mkvbs.recipe_service.exception.id_null.RecipeIdNullException
import com.mkvbs.recipe_service.exception.no_resource_found.NoIngredientsFoundForRecipeException
import com.mkvbs.recipe_service.utlis.ingredient.toEntityWithId
import com.mkvbs.recipe_service.utlis.ingredient.toResponseDto

fun Recipe.toEntity(): RecipeEntity {
    if (numberOfIngredients == null) throw NoIngredientsFoundForRecipeException("ID not set yet")
    val ingredientsEntities = ingredients.map { it.toEntityWithId() }.toMutableList()
    return RecipeEntity(
        null,
        ingredientsEntities, name, description, steps, ingredientsEntities.size
    )
}

fun Recipe.toEntityWithId(): RecipeEntity {
    if (id == null) throw RecipeIdNullException()
    if (numberOfIngredients == null) throw NoIngredientsFoundForRecipeException("ID not set yet")
    val ingredientsEntities = ingredients.map { it.toEntityWithId() }.toMutableList()
    return RecipeEntity(id, ingredientsEntities, name, description, steps,ingredientsEntities.size)
}

fun Recipe.toResponseDto(): RecipeResponseDto {
    if (id == null) throw RecipeIdNullException()
    return RecipeResponseDto(id, ingredients.map { it.toResponseDto() }, name, description, steps)
}