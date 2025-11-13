package com.example.supermarketapp.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class StoreService @Inject constructor(private val store: FirebaseFirestore) {

    companion object {
        const val ALL_PRODUCTS = "allProducts"
        const val LAST_PRODUCT = "lastProduct"
        const val STARRED_PRODUCTS = "starredProducts"
    }

    fun addProduct(city: HashMap<String, String>) {
//        store.collection(LAST_PRODUCT).get().addOnSuccessListener {
//            Log.i("Damian", it.documents.toString())
//        }.addOnFailureListener {
//            Log.i("Damian", "no ta funcando")
//        }
        store.collection("city").add(city)
    }

    fun getLastProduct() {

    }

}