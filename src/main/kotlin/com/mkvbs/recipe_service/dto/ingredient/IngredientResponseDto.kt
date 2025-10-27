package com.mkvbs.recipe_service.dto.ingredient

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

@Schema(
    name= "Ingredient Response",
    description = "Schema to hold ingredient information"
)
data class IngredientResponseDto (

    @field:Schema(
        description = "UUID of the ingredient",
        example = "3fa85f64-5717-4562-b3fc-2c963f66afa6"
    )
    val id: UUID,

    @field:Schema(
        description = "Name of the ingredient, only one ingredient with specific name can exists",
        example = "egg"
    )
    val name: String
)