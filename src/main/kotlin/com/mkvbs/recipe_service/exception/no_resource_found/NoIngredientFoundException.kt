package com.mkvbs.recipe_service.exception.no_resource_found

class NoIngredientFoundException(typeOfAttribute: String, attribute: String): NoResourceFoundException("No ingredient found with $typeOfAttribute $attribute")