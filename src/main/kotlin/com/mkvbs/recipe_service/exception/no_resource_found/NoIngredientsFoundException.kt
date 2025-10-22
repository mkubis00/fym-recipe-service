package com.mkvbs.recipe_service.exception.no_resource_found

class NoIngredientsFoundException(typeOfAttributes: String, attributesList: String): NoResourceFoundException("Ingredients", typeOfAttributes, attributesList)