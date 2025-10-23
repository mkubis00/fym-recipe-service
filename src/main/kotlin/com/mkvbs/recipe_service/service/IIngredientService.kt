package com.mkvbs.recipe_service.service

import com.mkvbs.recipe_service.domain.Ingredient
import java.util.UUID

interface IIngredientService {

    fun addIngredient(ingredientToSave: Ingredient): Ingredient

    fun deleteIngredient(id: UUID): Ingredient
    fun updateIngredient(ingredientToUpdate: Ingredient): Ingredient

    fun getIngredientById(id: UUID): Ingredient
    fun getIngredientByName(name: String): Ingredient
    fun getIngredients(ingredientIds: List<UUID>): List<Ingredient>
}