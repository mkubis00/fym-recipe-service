package com.mkvbs.recipe_service.service

import com.mkvbs.recipe_service.dto.IngredientDto
import com.mkvbs.recipe_service.dto.IngredientResponseDto
import java.util.UUID

interface IIngredientService {

    fun addIngredient(ingredientDto: IngredientDto): IngredientResponseDto
    fun getIngredientById(id: UUID): IngredientResponseDto
    fun getIngredientByName(name: String): IngredientResponseDto
    fun deleteIngredient(id: UUID): IngredientResponseDto
    fun updateIngredient(ingredientDto: IngredientDto): IngredientResponseDto
}