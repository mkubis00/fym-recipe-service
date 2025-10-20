package com.mkvbs.ingredient.utlis

import com.mkvbs.ingredient.dto.IngredientResponseDto
import com.mkvbs.ingredient.entity.IngredientEntity
import com.mkvbs.ingredient.exception.ResourceIdNullException

fun IngredientEntity.toResponseDto(): IngredientResponseDto {
    if (id != null) {
        return IngredientResponseDto(id, name)
    }
    throw ResourceIdNullException()
}