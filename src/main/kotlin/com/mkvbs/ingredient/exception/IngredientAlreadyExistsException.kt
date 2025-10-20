package com.mkvbs.ingredient.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.UUID

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class IngredientAlreadyExistsException: FymException {
    constructor (id: UUID): super("Ingredient with id $id already exists")
    constructor (name: String): super("Ingredient with name $name already exists")
}