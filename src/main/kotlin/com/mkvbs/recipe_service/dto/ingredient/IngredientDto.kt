package com.mkvbs.recipe_service.dto.ingredient

import java.util.UUID

data class IngredientDto (
    val id: UUID? = null,
    val name: String
)