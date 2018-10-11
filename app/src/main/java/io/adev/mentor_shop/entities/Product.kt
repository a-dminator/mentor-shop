package io.adev.mentor_shop.entities

import kotlinx.serialization.Serializable

@Serializable
class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val imageUrl: String
)