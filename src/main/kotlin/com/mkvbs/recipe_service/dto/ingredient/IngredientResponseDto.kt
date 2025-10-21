package com.mkvbs.recipe_service.dto.ingredient

import java.util.UUID

data class IngredientResponseDto (
    val id: UUID,
    val name: String
)