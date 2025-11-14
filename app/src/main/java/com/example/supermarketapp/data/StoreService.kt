package com.example.supermarketapp.data

import com.example.supermarketapp.domain.models.Product
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StoreService @Inject constructor(private val store: FirebaseFirestore, private val storageService: StorageService) {

    companion object {
        const val ALL_PRODUCTS = "allProducts"
        const val LAST_PRODUCT = "lastProduct"
        const val STARRED_PRODUCTS = "starredProducts"
    }

    suspend fun addProduct(product: Product, isStarred: Boolean) {

        storageService.uploadImage(product.imageUri, product.name)

        // Get if there is last product
        val snapshot = store.collection(LAST_PRODUCT).get().await()
        // If have last product, delete and enter the new one
        if(snapshot.documents.isNotEmpty()) snapshot.documents.first().reference.delete().await()
        store.collection(LAST_PRODUCT).add(product).await()

        // Add product on all products collection
        store.collection(ALL_PRODUCTS).add(product).await()

        // If starred, add to the starred products collection
        if(isStarred) store.collection(STARRED_PRODUCTS).add(product).await()

    }

    fun getLastProduct() {

    }

}