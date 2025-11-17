package com.example.supermarketapp.data

import androidx.core.net.toUri
import com.example.supermarketapp.domain.models.Product
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StoreService @Inject constructor(
    private val store: FirebaseFirestore,
    private val storageService: StorageService
) {

    companion object {
        const val ALL_PRODUCTS = "allProducts"
        const val LAST_PRODUCT = "lastProduct"
        const val STARRED_PRODUCTS = "starredProducts"
    }

    suspend fun addProduct(product: Product, isStarred: Boolean) {

        storageService.uploadImage(product.imageUri!!.toUri(), product.id.orEmpty())

        // Get if there is last product
        val snapshot = store.collection(LAST_PRODUCT).get().await()
        // If have last product, delete and enter the new one
        if (snapshot.documents.isNotEmpty()) snapshot.documents.first().reference.delete().await()
        store.collection(LAST_PRODUCT).add(product).await()

        // Add product on all products collection
        store.collection(ALL_PRODUCTS).add(product).await()

        // If starred, add to the starred products collection
        if (isStarred) store.collection(STARRED_PRODUCTS).add(product).await()

    }

    suspend fun getLastProduct():Product? {
        val collection = store.collection(LAST_PRODUCT).get().await()
        if(collection.size() == 0) return null
        val product = collection.documents.elementAt(0).toObject<Product>()
        val image = storageService.downloadImage(product?.id!!)
        return product.copy(imageUri = image.toString())
    }

    suspend fun getStarredProducts():List<Product?> {
        val collection = store.collection(STARRED_PRODUCTS).get().await()
        val productList = collection.documents.map { document ->
            document.toObject<Product>()
        }
        val newProductList = productList.map { product ->
            product?.copy(imageUri = storageService.downloadImage(product.id!!).toString())
        }
        return newProductList
    }

    suspend fun getAllProducts():List<Product?> {
        val collection = store.collection(ALL_PRODUCTS).get().await()
        val productList = collection.documents.map { document ->
            document.toObject<Product>()
        }
        val newProductList =  productList.map { product ->
            product?.copy(imageUri = storageService.downloadImage(product.id!!).toString())
        }
        return newProductList
    }

}