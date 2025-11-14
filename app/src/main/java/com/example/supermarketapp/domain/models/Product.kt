package com.example.supermarketapp.domain.models

import android.net.Uri

data class Product(
    val id:String,
    val imageUri:Uri,
    val name:String,
    val description:String,
    val price:Int,
)
