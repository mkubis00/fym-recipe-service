package com.mkvbs.recipe_service.dto.recipe

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import java.util.UUID

data class RecipeDto(
    val id: UUID? = null,

    @field:Size(min = 3, message = "Recipe must contain at least 3 ingredients")
    val ingredients: List<UUID>,

    @field:NotEmpty(message = "Name can not be null or empty")
    @field:Size(min = 3, max = 30, message = "Name can not be less than 3 and not greater than 30 characters")
    val name: String,

    @field:NotEmpty(message = "Description can not be null or empty")
    @field:Size(min = 30, max = 150, message = "Description can not be less than 30 and not greater than 150 characters")
    val description: String,

    @field:Size(min = 3, message = "Recipe must contain at least 3 steps")
    val steps: List<String>,
)