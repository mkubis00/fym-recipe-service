package com.mkvbs.recipe_service.controller

import com.mkvbs.recipe_service.domain.Recipe
import com.mkvbs.recipe_service.dto.recipe.RecipeDto
import com.mkvbs.recipe_service.dto.recipe.RecipeResponseDto
import com.mkvbs.recipe_service.service.IRecipeService
import com.mkvbs.recipe_service.utlis.recipe.toDomain
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/recipe/v1", produces = [MediaType.APPLICATION_JSON_VALUE])
class RecipeController(
    private val recipeService: IRecipeService,
) {

//    @PostMapping("/addRecipe")
//    fun addRecipe(recipeToCreate: RecipeDto): ResponseEntity<RecipeResponseDto> {
//        val recipeDomainToCreate = recipeToCreate.toDomain()
//    }
}