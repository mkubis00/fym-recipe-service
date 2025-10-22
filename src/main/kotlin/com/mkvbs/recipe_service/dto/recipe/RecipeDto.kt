package com.mkvbs.recipe_service.dto.recipe

import java.util.UUID

data class RecipeDto(
    val id: UUID? = null,
    val ingredients: List<UUID>,
    val name: String,
    val description: String,
    val steps: List<String>
)