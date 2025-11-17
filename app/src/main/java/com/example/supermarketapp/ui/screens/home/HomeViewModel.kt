package com.example.supermarketapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supermarketapp.data.StoreService
import com.example.supermarketapp.domain.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val storeService: StoreService):ViewModel() {

    private val _lastProduct = MutableStateFlow<Product?>(null)
    val lastProduct:StateFlow<Product?> = _lastProduct

    private val _starredProducts = MutableStateFlow<List<Product?>?>(null)
    val starredProducts:StateFlow<List<Product?>?> = _starredProducts

    private val _allProducts = MutableStateFlow<List<Product?>?>(null)
    val allProducts:StateFlow<List<Product?>?> = _allProducts

    fun getLastProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            _lastProduct.value = storeService.getLastProduct()
        }
    }

    fun getStarredProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            _starredProducts.value = storeService.getStarredProducts()
        }
    }

    fun getAllProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            _allProducts.value = storeService.getAllProducts()
        }
    }

}

sealed class UIState() {
    val lastProduct:Product? = null
    val starredProducts:List<Product>? = null
    val allProducts:List<Product>? = null
}