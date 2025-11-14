package com.example.supermarketapp.ui.screens.addproduct

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.supermarketapp.domain.models.Product
import com.example.supermarketapp.ui.generics.SubtitleText
import com.example.supermarketapp.ui.generics.TextFieldItem
import com.example.supermarketapp.ui.generics.TitleText
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID

@Composable
fun AddProductScreen(
    innerPadding: PaddingValues,
    viewModel: AddProductViewModel = hiltViewModel(),
    onBackClicked: () -> Unit
) {

    var nameProduct by rememberSaveable { mutableStateOf("") }
    var productDescription by rememberSaveable { mutableStateOf("") }
    var priceProduct by rememberSaveable { mutableStateOf("") }
    var imageUri: Uri? by rememberSaveable { mutableStateOf(null) }

    val coroutineScope = rememberCoroutineScope()
    var isBackButtonClicked by rememberSaveable { mutableStateOf(false) }
    var starProduct by rememberSaveable { mutableStateOf(false) }


    val imageLauncher = rememberLauncherForActivityResult(GetContent()) { uri ->
        uri?.let {
            if (uri.path?.isNotBlank() == true) imageUri = uri
        }
    }

    val isLoading by viewModel.isLoading.collectAsState()
    val context = LocalContext.current

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
                        if (isBackButtonClicked) return@clickable
                        isBackButtonClicked = true
                        onBackClicked()
                        // Avoid problems for multiple touch
                        coroutineScope.launch {
                            delay(500)
                            isBackButtonClicked = false
                        }
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
            StarredOption(starProduct) { starProduct = !starProduct }
            Button(
                onClick = { imageLauncher.launch("image/*") },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                )
            ) {
                SubtitleText("Añadir imagen")
            }
            if (imageUri?.path?.isNotBlank() == true) {
                AsyncImage(
                    model = imageUri,
                    contentDescription = "",
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    if (nameProduct.isNotBlank() && productDescription.isNotBlank() && priceProduct.isNotBlank() && imageUri?.path!!.isNotBlank()) {
                        viewModel.addProduct(
                            Product(
                                id = generateProductId(),
                                imageUri = imageUri!!,
                                name = nameProduct,
                                description = productDescription,
                                price = priceProduct.toInt()
                            ),
                            isStarred = starProduct
                        ) {
                            nameProduct = ""
                            productDescription = ""
                            priceProduct = ""
                            starProduct = false
                            imageUri = null
                            Toast.makeText(context, "Producto añadido!", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green.copy(.5f)
                )
            ) {
                SubtitleText("Añadir producto")
            }
            Spacer(modifier = Modifier.size(32.dp))
        }
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(.3f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun StarredOption(isChecked: Boolean, onCheckClicked: () -> Unit) {

    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
        Row(
            Modifier
                .clickable { onCheckClicked() }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { onCheckClicked() },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Green.copy(alpha = .7f)
                )
            )
            SubtitleText("Destacar producto")
        }
    }
}

private fun generateProductId(): String {
    return UUID.randomUUID().toString()
}

