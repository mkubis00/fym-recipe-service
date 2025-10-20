package com.mkvbs.ingredient.utlis

import com.mkvbs.ingredient.dto.IngredientDto
import com.mkvbs.ingredient.entity.IngredientEntity

fun IngredientDto.toEntity(): IngredientEntity {
    return IngredientEntity(null, name.lowercase())
}

fun IngredientDto.toEntityWithId(): IngredientEntity {
    return IngredientEntity(id, name.lowercase())
}
