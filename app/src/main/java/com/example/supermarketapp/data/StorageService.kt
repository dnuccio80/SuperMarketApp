package com.example.supermarketapp.data

import android.net.Uri
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

class StorageService @Inject constructor(private val storageReference: StorageReference) {


    fun uploadImage(imageUri:Uri, productName:String) {
        storageReference.child(productName).putFile(imageUri)
    }

}