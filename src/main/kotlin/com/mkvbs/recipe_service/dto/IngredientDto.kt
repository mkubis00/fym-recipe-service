package com.mkvbs.recipe_service.dto

import java.util.UUID

data class IngredientDto (
    val id: UUID? = null,
    val name: String
)