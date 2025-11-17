package com.example.supermarketapp.domain.models

import android.net.Uri

data class Product(
    val id:String? = null,
    val imageUri:String? = null,
    val name:String? = null,
    val description:String? = null,
    val price:Int? = null,
)
