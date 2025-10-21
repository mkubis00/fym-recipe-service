package com.mkvbs.recipe_service.utlis

import com.mkvbs.recipe_service.domain.Ingredient
import com.mkvbs.recipe_service.entity.IngredientEntity
import com.mkvbs.recipe_service.exception.ResourceIdNullException

fun IngredientEntity.toDomain(): Ingredient {
    if (id == null) throw ResourceIdNullException()
    return Ingredient(id, name)
}