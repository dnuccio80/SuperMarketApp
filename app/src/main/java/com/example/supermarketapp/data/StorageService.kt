package com.example.supermarketapp.data

import android.net.Uri
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageService @Inject constructor(private val storageReference: StorageReference) {

    fun uploadImage(imageUri:Uri, productId:String) {
        storageReference.child(productId).putFile(imageUri)
    }

    suspend fun downloadImage(productName: String):Uri? {
        return storageReference.child(productName).downloadUrl.await()
    }

}