package com.mkvbs.recipe_service.utlis.ingredient

import com.mkvbs.recipe_service.domain.Ingredient
import com.mkvbs.recipe_service.dto.ingredient.IngredientDto
import com.mkvbs.recipe_service.exception.id_null.IngredientIdNullException

fun IngredientDto.toDomain(): Ingredient {
    return Ingredient(null, name.lowercase())
}

fun IngredientDto.toDomainWithId(): Ingredient {
    if(id == null) throw IngredientIdNullException()
    return Ingredient(id, name.lowercase())
}
