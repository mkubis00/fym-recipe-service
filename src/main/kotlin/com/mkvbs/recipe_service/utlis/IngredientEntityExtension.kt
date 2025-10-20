package com.mkvbs.recipe_service.utlis

import com.mkvbs.recipe_service.dto.IngredientResponseDto
import com.mkvbs.recipe_service.entity.IngredientEntity
import com.mkvbs.recipe_service.exception.ResourceIdNullException

fun IngredientEntity.toResponseDto(): IngredientResponseDto {
    if (id != null) {
        return IngredientResponseDto(id, name)
    }
    throw ResourceIdNullException()
}