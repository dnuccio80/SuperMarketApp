package com.example.supermarketapp.data.response

import com.example.supermarketapp.domain.models.Product

data class ProductResponse(
    val id: String? = null,
    val imageUri: String? = null,
    val name: String? = null,
    val description: String? = null,
    val price: Int? = null,
) {
    fun toDomain(): Product {
        return Product(
            id = id,
            imageUri = imageUri,
            name = name,
            description = description,
            price = price,
        )
    }
}
