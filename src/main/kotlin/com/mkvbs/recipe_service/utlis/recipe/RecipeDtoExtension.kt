package com.mkvbs.recipe_service.utlis.recipe

import com.mkvbs.recipe_service.domain.Ingredient
import com.mkvbs.recipe_service.domain.Recipe
import com.mkvbs.recipe_service.dto.recipe.RecipeDto
import com.mkvbs.recipe_service.exception.id_null.RecipeIdNullException

fun RecipeDto.toDomain(ingredients: MutableList<Ingredient>): Recipe {
    return Recipe(null, ingredients, name, description, steps)
}

fun RecipeDto.toDomainWithId(ingredients: MutableList<Ingredient>): Recipe {
    if(id == null) throw RecipeIdNullException()
    return Recipe(null, ingredients, name, description, steps)
}