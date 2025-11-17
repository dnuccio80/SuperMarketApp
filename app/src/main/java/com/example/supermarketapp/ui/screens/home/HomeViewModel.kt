package com.example.supermarketapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supermarketapp.data.StoreService
import com.example.supermarketapp.domain.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val storeService: StoreService) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState?> = _uiState

    fun getUIInfo() {
        getLastProduct()
        getStarredProducts()
        getAllProducts()
    }

    private fun getLastProduct() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                storeService.getLastProduct()
            }
            _uiState.update { it.copy(lastProduct = result) }
        }
    }

    private fun getStarredProducts() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                storeService.getStarredProducts()
            }
            _uiState.update { it.copy(starredProducts = result) }
        }
    }

    private fun getAllProducts() {
        viewModelScope.launch {

            val result = withContext(Dispatchers.IO) {
                storeService.getAllProducts()
            }
            _uiState.update { it.copy(allProducts = result) }
        }
    }

}

data class UIState(
    val lastProduct: Product? = null,
    val starredProducts: List<Product?> = emptyList(),
    val allProducts: List<Product?> = emptyList(),
)
