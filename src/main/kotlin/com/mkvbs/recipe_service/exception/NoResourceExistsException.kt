package com.mkvbs.recipe_service.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.UUID

@ResponseStatus(HttpStatus.NOT_FOUND)
class NoResourceExistsException : FymException {
    constructor(id: UUID): super("Resource with id $id does not exists")
    constructor(name: String): super("Resource with name $name does not exists")
}