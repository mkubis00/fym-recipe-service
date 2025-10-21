package com.mkvbs.recipe_service.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.UUID

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class ResourceAlreadyExistsException: FymException {
    constructor (id: UUID): super("Resource with id $id already exists")
    constructor (name: String): super("resource with name $name already exists")
}