package com.mkvbs.recipe_service.utlis.ingredient

import com.mkvbs.recipe_service.domain.Ingredient
import com.mkvbs.recipe_service.entity.IngredientEntity
import com.mkvbs.recipe_service.exception.id_null.IngredientIdNullException

fun IngredientEntity.toDomain(): Ingredient {
    if (id == null) throw IngredientIdNullException()
    return Ingredient(id, name)
}