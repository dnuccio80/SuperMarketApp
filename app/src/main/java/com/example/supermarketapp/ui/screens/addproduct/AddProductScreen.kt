package com.example.supermarketapp.ui.screens.addproduct

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import coil.compose.AsyncImage
import com.example.supermarketapp.ui.generics.BodyText
import com.example.supermarketapp.ui.generics.SubtitleText
import com.example.supermarketapp.ui.generics.TextFieldItem
import com.example.supermarketapp.ui.generics.TitleText

@Composable
fun AddProductScreen(innerPadding: PaddingValues) {

    var nameProduct by rememberSaveable { mutableStateOf("") }
    var productDescription by rememberSaveable { mutableStateOf("") }
    var priceProduct by rememberSaveable { mutableStateOf("") }
    var imageUri by rememberSaveable { mutableStateOf("") }

    val imageLauncher = rememberLauncherForActivityResult(GetContent()) { uri ->
        uri?.let { imageUri = uri.toString() }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp, horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back button",
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        // Back to Home Screen
                        TODO()
                    })
            }
            Spacer(modifier = Modifier.weight(1f))
            TitleText("Añadir un producto")
            TextFieldItem(value = nameProduct, label = "Nombre")
            { newValue ->
                nameProduct = newValue
            }
            TextFieldItem(value = productDescription, label = "Descripción")
            { newValue ->
                productDescription = newValue
            }
            TextFieldItem(value = priceProduct, label = "Precio", isInt = true)
            { newValue ->
                priceProduct = newValue
            }
            Spacer(modifier = Modifier.size(4.dp))
            Button(
                onClick = { imageLauncher.launch("image/*") },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                )
            ) {
                SubtitleText("Añadir imagen")
            }
            if (imageUri.isNotBlank()) {
                AsyncImage(
                    model = imageUri,
                    contentDescription = "",
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green.copy(.5f)
                )
            ) {
                SubtitleText("Añadir producto")
            }
            Spacer(modifier = Modifier.size(32.dp))
        }
    }

}

