package com.mkvbs.ingredient.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class ResourceIdNullException() : FymException("Resource ID can not be null.")