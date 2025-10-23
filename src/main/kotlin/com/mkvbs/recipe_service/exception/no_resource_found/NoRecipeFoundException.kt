package com.mkvbs.recipe_service.exception.no_resource_found

class NoRecipeFoundException(typeOfAttribute: String, attribute: String): NoResourceFoundException("No recipe found with $typeOfAttribute $attribute")