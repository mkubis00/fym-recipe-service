package com.mkvbs.recipe_service.exception.id_null

import com.mkvbs.recipe_service.exception.FymException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
open class ResourceIdNullException(resource: String) : FymException("$resource ID can not be null.")