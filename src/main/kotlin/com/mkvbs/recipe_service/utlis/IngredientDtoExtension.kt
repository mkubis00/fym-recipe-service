package com.mkvbs.recipe_service.utlis

import com.mkvbs.recipe_service.domain.Ingredient
import com.mkvbs.recipe_service.dto.ingredient.IngredientDto

fun IngredientDto.toDomain(): Ingredient {
    return Ingredient(null, name.lowercase())
}

fun IngredientDto.toDomainWithId(): Ingredient {
    return Ingredient(id, name.lowercase())
}
