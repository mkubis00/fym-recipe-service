package com.mkvbs.recipe_service.controller

import com.mkvbs.recipe_service.dto.ErrorResponseDto
import com.mkvbs.recipe_service.dto.recipe.RecipeDto
import com.mkvbs.recipe_service.dto.recipe.RecipeResponseDto
import com.mkvbs.recipe_service.service.IRecipeService
import com.mkvbs.recipe_service.utlis.contentToResponseDto
import com.mkvbs.recipe_service.utlis.recipe.toDomain
import com.mkvbs.recipe_service.utlis.recipe.toDomainWithId
import com.mkvbs.recipe_service.utlis.recipe.toResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@Tag(
    name = "CRUD REST APIs for Recipes",
    description = "Rest Api to create, fetch, update and delete recipes"
)
@RestController
@RequestMapping("/api/recipe/v1", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class RecipeController(
    private val recipeService: IRecipeService,
) {

    @Operation(
        summary = "Create Recipe REST API",
        description = "REST API to create new recipe"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status.CREATED"
    )
    @PostMapping("/addRecipe")
    fun addRecipe(@Valid @RequestBody recipeToCreate: RecipeDto): ResponseEntity<RecipeResponseDto> {
        val recipeDomainToCreate = recipeToCreate.toDomain()
        val savedRecipe = recipeService.addRecipe(recipeDomainToCreate, recipeToCreate.ingredients)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipe.toResponseDto())
    }

    @Operation(
        summary = "Fetch Recipe REST API",
        description = "REST API to fetch recipe"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status.OK"
    )
    @GetMapping("/getRecipeById/{id}")
    fun getRecipeById(@PathVariable id: UUID): ResponseEntity<RecipeResponseDto> {
        val recipe = recipeService.getRecipe(id)
        return ResponseEntity.status(HttpStatus.OK).body(recipe.toResponseDto())
    }

    @Operation(
        summary = "Update Recipe REST API",
        description = "REST API to update recipe"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status.OK"
    )
    @PutMapping("/updateRecipe")
    fun updateRecipe(@Valid @RequestBody recipeDto: RecipeDto): ResponseEntity<RecipeResponseDto> {
        val recipeToUpdate = recipeDto.toDomainWithId()
        val updatedRecipeDto = recipeService.updateRecipe(recipeToUpdate, recipeDto.ingredients).toResponseDto()
        return ResponseEntity.status(HttpStatus.OK).body(updatedRecipeDto)
    }

    @Operation(
        summary = "Delete Recipe REST API",
        description = "REST API to delete recipe"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status.OK"
    )
    @DeleteMapping("/deleteRecipe/{id}")
    fun deleteRecipe(@PathVariable id: UUID): ResponseEntity<RecipeResponseDto> {
        val deletedRecipeDto = recipeService.deleteRecipe(id).toResponseDto()
        return ResponseEntity.status(HttpStatus.OK).body(deletedRecipeDto)
    }

    @Operation(
        summary = "Get recipes with specified requirements",
        description = "Returns a pageable list of recipes with defined requirements."
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "List of recipes successfully returned."
            ),
            ApiResponse(
                responseCode = "500",
                description = "Unexpected server error while retrieving recipes.",
                content = [Content(
                    schema = Schema(
                        implementation = ErrorResponseDto::class
                    )
                )]
            )
        ]
    )
    @GetMapping("get-recipes")
    fun getRecipes(
        @RequestParam("page", required = true) page: Int,
        @RequestParam("page_size", required = true) pageSize: Int,
        @RequestParam("min_ingredient count", required = false) minIngredientCount: Int?,
        @RequestParam("max_ingredient count", required = false) maxIngredientCount: Int?,
        @RequestParam("min steps count", required = true) minStepsCount: Int?,
        @RequestParam("max steps count", required = true) maxStepsCount: Int?,
        @RequestParam("includedIngredientsIds", required = false) includedIngredientsIds: Set<UUID>?,
        @RequestParam("excludedIngredientsIds", required = false) excludedIngredientsIds: Set<UUID>?,
    ): ResponseEntity<Page<RecipeResponseDto>> {
        val pageOfFoundRecipes = recipeService.getRecipes(
            page,
            pageSize,
            minIngredientCount,
            maxIngredientCount,
            minStepsCount,
            maxStepsCount,
            includedIngredientsIds,
            excludedIngredientsIds
        )
        val pageOfFoundRecipesResponseDto = pageOfFoundRecipes.contentToResponseDto()
        return ResponseEntity.status(HttpStatus.OK).body(pageOfFoundRecipesResponseDto)
    }
}