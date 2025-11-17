package com.example.supermarketapp.domain.models

import android.net.Uri
import com.example.supermarketapp.data.response.ProductResponse

data class Product(
    val id: String?,
    val imageUri: String?,
    val name: String?,
    val description: String?,
    val price: Int?,
) {
    fun toData(): ProductResponse {
        return ProductResponse(
            id = id,
            imageUri = imageUri,
            name = name,
            description = description,
            price = price
        )
    }
}
