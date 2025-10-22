package com.mkvbs.recipe_service.exception.no_resource_found

import com.mkvbs.recipe_service.exception.FymException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
open class NoResourceFoundException(resource: String, typeOfAttribute: String, attribute: String) : FymException("$resource with $typeOfAttribute $attribute not found")