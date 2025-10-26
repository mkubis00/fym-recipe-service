package com.mkvbs.recipe_service.controller

import com.mkvbs.recipe_service.dto.recipe.RecipeDto
import com.mkvbs.recipe_service.dto.recipe.RecipeResponseDto
import com.mkvbs.recipe_service.service.IRecipeService
import com.mkvbs.recipe_service.utlis.recipe.toDomain
import com.mkvbs.recipe_service.utlis.recipe.toDomainWithId
import com.mkvbs.recipe_service.utlis.recipe.toResponseDto
import jakarta.validation.Valid
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

@RestController
@RequestMapping("/api/recipe/v1", produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class RecipeController(
    private val recipeService: IRecipeService,
) {

    @PostMapping("/addRecipe")
    fun addRecipe(@Valid @RequestBody recipeToCreate: RecipeDto): ResponseEntity<RecipeResponseDto> {
        val recipeDomainToCreate = recipeToCreate.toDomain()
        val savedRecipe = recipeService.addRecipe(recipeDomainToCreate, recipeToCreate.ingredients)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipe.toResponseDto())
    }

    @GetMapping("/getRecipeById/{id}")
    fun getRecipeById(@PathVariable id: UUID): ResponseEntity<RecipeResponseDto> {
        val recipe = recipeService.getRecipe(id)
        return ResponseEntity.status(HttpStatus.OK).body(recipe.toResponseDto())
    }

    @PutMapping("/updateRecipe")
    fun updateRecipe(@Valid @RequestBody recipeDto: RecipeDto): ResponseEntity<RecipeResponseDto> {
        val recipeToUpdate = recipeDto.toDomainWithId()
        val updatedRecipeDto = recipeService.updateRecipe(recipeToUpdate, recipeDto.ingredients).toResponseDto()
        return ResponseEntity.status(HttpStatus.OK).body(updatedRecipeDto)
    }

    @DeleteMapping("/deleteRecipe/{id}")
    fun deleteRecipe(@PathVariable id: UUID): ResponseEntity<RecipeResponseDto> {
        val deletedRecipeDto = recipeService.deleteRecipe(id).toResponseDto()
        return ResponseEntity.status(HttpStatus.OK).body(deletedRecipeDto)
    }
}