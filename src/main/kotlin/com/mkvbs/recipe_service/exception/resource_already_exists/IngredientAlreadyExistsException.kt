package com.mkvbs.recipe_service.exception.resource_already_exists

class IngredientAlreadyExistsException(name: String): ResourceAlreadyExistsException("Ingredient", name)
