package com.mkvbs.recipe_service.dto.ingredient

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import java.util.UUID

@Schema(
    name= "Ingredient",
    description = "Schema to hold ingredient information"
)
data class IngredientDto (

    @field:Schema(
        description = "UUID of the ingredient, should not be included during creation of the ingredient",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    val id: UUID? = null,

    @field:Schema(
        description = "Name of the ingredient, only one ingredient with specific name can exists",
        example = "egg"
    )
    @field:NotEmpty(message = "Name can not be null or empty")
    @field:Size(min = 3, max = 30, message = "Name can not be less than 3 and not greater than 30 characters")
    val name: String
)