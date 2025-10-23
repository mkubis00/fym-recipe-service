package com.mkvbs.recipe_service.exception.no_resource_found

class NoIngredientsFoundForRecipeException(recipeId: String) : NoResourceFoundException("Not all ingredients where found for recipe $recipeId")