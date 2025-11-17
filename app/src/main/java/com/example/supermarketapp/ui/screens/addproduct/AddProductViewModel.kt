package com.example.supermarketapp.ui.screens.addproduct

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supermarketapp.data.StoreService
import com.example.supermarketapp.domain.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(private val storeService: StoreService) :
    ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun addProduct(product: Product, isStarred: Boolean, onWorkFinish: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            withContext(Dispatchers.IO) {
                try {
                    storeService.addProduct(product.toData(), isStarred)
                } catch (e: Exception) {
                    Log.i("Damian", e.toString())
                }
            }
            _isLoading.value = false
            onWorkFinish()
        }
    }

}