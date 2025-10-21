package com.mkvbs.recipe_service.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class ResourceIdNullException : FymException {
    constructor() : super("Resource ID can not be null.")
    constructor(objectWithoutId: Any) : super("Resource ID for class ${objectWithoutId::class.simpleName} can not be null, object $objectWithoutId")
}