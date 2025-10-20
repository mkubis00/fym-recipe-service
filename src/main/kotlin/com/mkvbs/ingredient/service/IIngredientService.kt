package com.mkvbs.ingredient.service

import com.mkvbs.ingredient.dto.IngredientDto
import com.mkvbs.ingredient.dto.IngredientResponseDto
import org.springframework.http.ResponseEntity
import java.util.UUID

interface IIngredientService {

    fun addIngredient(ingredientDto: IngredientDto): IngredientResponseDto
    fun getIngredientById(id: UUID): IngredientResponseDto
    fun getIngredientByName(name: String): IngredientResponseDto
    fun deleteIngredient(id: UUID): IngredientResponseDto
    fun updateIngredient(ingredientDto: IngredientDto): IngredientResponseDto
}