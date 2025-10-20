package com.mkvbs.recipe_service.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class ResourceIdNullException() : FymException("Resource ID can not be null.")