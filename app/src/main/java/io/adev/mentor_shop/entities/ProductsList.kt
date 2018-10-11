package io.adev.mentor_shop.entities

import kotlinx.serialization.Serializable

@Serializable
class ProductsList(
    val products: List<Product>
)