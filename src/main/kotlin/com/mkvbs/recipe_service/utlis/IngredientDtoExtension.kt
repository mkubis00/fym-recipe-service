package com.mkvbs.recipe_service.utlis

import com.mkvbs.recipe_service.dto.IngredientDto
import com.mkvbs.recipe_service.entity.IngredientEntity

fun IngredientDto.toEntity(): IngredientEntity {
    return IngredientEntity(null, name.lowercase())
}

fun IngredientDto.toEntityWithId(): IngredientEntity {
    return IngredientEntity(id, name.lowercase())
}
