package com.mkvbs.recipe_service.exception.resource_already_exists

import com.mkvbs.recipe_service.exception.FymException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
open class ResourceAlreadyExistsException(resource: String, name: String): FymException("$resource with name $name already exists")
