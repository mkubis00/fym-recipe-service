package com.mkvbs.recipe_service.exception.no_resource_found

class NoRecipeFoundException(typeOfAttribute: String, attribute: String): NoResourceFoundException("Recipe", typeOfAttribute, attribute) {
}