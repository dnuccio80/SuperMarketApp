package com.example.supermarketapp.ui.routes

sealed class Routes(val route:String) {
    data object Home:Routes("home")
    data object AddProduct:Routes("addProduct")
}
