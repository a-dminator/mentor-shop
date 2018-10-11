package io.adev.mentor_shop.entities

import kotlinx.serialization.Serializable

@Serializable
class CategoriesList(
    val categories: List<Category>
)