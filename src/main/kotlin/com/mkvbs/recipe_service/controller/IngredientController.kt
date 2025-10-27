package com.mkvbs.recipe_service.controller

import com.mkvbs.recipe_service.dto.ingredient.IngredientDto
import com.mkvbs.recipe_service.dto.ingredient.IngredientResponseDto
import com.mkvbs.recipe_service.service.IIngredientService
import com.mkvbs.recipe_service.utlis.ingredient.toDomain
import com.mkvbs.recipe_service.utlis.ingredient.toDomainWithId
import com.mkvbs.recipe_service.utlis.ingredient.toResponseDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
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
import org.springframework.web.bind.annotation.RestController

import java.util.UUID

@Tag(
    name = "CRUD REST APIs for Ingredients",
    description = "Rest Api to create, fetch, update and delete ingredients"
)
@RestController
@RequestMapping("/api/ingredient/v1", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class IngredientController(
    private val ingredientService: IIngredientService
){

    @Operation(
        summary = "Create Ingredient REST API",
        description = "REST API to create new ingredient"
    )
    @ApiResponse(
        responseCode = "201",
        description = "HTTP Status.CREATED"
    )
    @PostMapping("/addIngredient")
    fun addIngredient(@Valid @RequestBody ingredientDto: IngredientDto): ResponseEntity<IngredientResponseDto> {
        val savedIngredient = ingredientService.addIngredient(ingredientDto.toDomain())
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIngredient.toResponseDto())
    }

    @Operation(
        summary = "Update Ingredient REST API",
        description = "REST API to update ingredient"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status.OK"
    )
    @PutMapping("/updateIngredient")
    fun updateIngredient(@Valid @RequestBody ingredientDto: IngredientDto): ResponseEntity<IngredientResponseDto> {
        val updatedIngredient = ingredientService.updateIngredient(ingredientDto.toDomainWithId())
        return ResponseEntity.status(HttpStatus.OK).body(updatedIngredient.toResponseDto())
    }

    @Operation(
        summary = "Delete Ingredient REST API",
        description = "REST API to delete ingredient"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status.OK"
    )
    @DeleteMapping("/deleteIngredient/{id}")
    fun deleteIngredient(@PathVariable id: UUID): ResponseEntity<IngredientResponseDto> {
        val deletedIngredient = ingredientService.deleteIngredient(id)
        return ResponseEntity.status(HttpStatus.OK).body(deletedIngredient.toResponseDto())
    }

    @Operation(
        summary = "Fetch Ingredient REST API",
        description = "REST API to fetch ingredient by ID"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status.OK"
    )
    @GetMapping("/ingredientById/{id}")
    fun getIngredientById(@PathVariable id: UUID): ResponseEntity<IngredientResponseDto> {
        val ingredient = ingredientService.getIngredientById(id)
        return ResponseEntity.status(HttpStatus.OK).body(ingredient.toResponseDto())
    }

    @Operation(
        summary = "Get Ingredient REST API",
        description = "REST API to fetch ingredient by name"
    )
    @ApiResponse(
        responseCode = "200",
        description = "HTTP Status.OK"
    )
    @GetMapping("/ingredientByName/{name}")
    fun getIngredientByName(@PathVariable  @NotEmpty(message = "Name can not be null or empty")
                            @Size(min = 3, max = 30, message = "Name can not be less than 3 and not greater than 30 characters") name: String): ResponseEntity<IngredientResponseDto> {
        val ingredient = ingredientService.getIngredientByName(name)
        return ResponseEntity.status(HttpStatus.OK).body(ingredient.toResponseDto())
    }


}