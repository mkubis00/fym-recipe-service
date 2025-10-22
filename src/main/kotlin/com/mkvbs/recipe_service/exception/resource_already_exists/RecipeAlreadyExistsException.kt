package com.mkvbs.recipe_service.exception.resource_already_exists

class RecipeAlreadyExistsException(name: String) : ResourceAlreadyExistsException("Recipe", name)