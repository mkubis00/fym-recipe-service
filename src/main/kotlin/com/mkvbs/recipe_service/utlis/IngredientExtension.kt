package com.mkvbs.recipe_service.utlis

import com.mkvbs.recipe_service.domain.Ingredient
import com.mkvbs.recipe_service.dto.ingredient.IngredientResponseDto
import com.mkvbs.recipe_service.entity.IngredientEntity
import com.mkvbs.recipe_service.exception.ResourceIdNullException

fun Ingredient.toEntity(): IngredientEntity {
    return IngredientEntity(null, name)
}

fun Ingredient.toEntityWithId(): IngredientEntity {
    if(id == null) throw ResourceIdNullException()
    return IngredientEntity(id, name)
}

fun Ingredient.toResponseDto(): IngredientResponseDto {
    if (id == null) throw ResourceIdNullException()
    return IngredientResponseDto(id, name)
}