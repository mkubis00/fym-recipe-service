package com.mkvbs.recipe_service.exception.no_resource_found

class NoIngredientsFoundException(typeOfAttributes: String, attributesList: String): NoResourceFoundException("No ingredients found with $typeOfAttributes $attributesList")