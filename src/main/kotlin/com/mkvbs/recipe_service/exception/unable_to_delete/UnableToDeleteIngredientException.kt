package com.mkvbs.recipe_service.exception.unable_to_delete

import com.mkvbs.recipe_service.exception.FymException
import java.util.UUID

class UnableToDeleteIngredientException(id: UUID) : FymException("Unable to delete ingredient: $id, because it is still used in recipes")