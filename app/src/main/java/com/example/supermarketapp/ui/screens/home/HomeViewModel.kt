package com.example.supermarketapp.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.supermarketapp.data.StoreService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val storeService: StoreService):ViewModel() {

    init {
        storeService.getLastProduct()
    }

}