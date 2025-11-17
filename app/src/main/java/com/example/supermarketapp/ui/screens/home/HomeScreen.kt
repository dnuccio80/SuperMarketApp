package com.example.supermarketapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.supermarketapp.domain.models.Product
import com.example.supermarketapp.ui.generics.BodyText
import com.example.supermarketapp.ui.generics.SubtitleText
import com.example.supermarketapp.ui.generics.TitleText

@Composable
fun HomeScreen(
    innerPadding: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel(),
    onAddProductButtonClicked: () -> Unit,
) {

    LaunchedEffect(true) {
        viewModel.getLastProduct()
        viewModel.getStarredProducts()
        viewModel.getAllProducts()
    }

    val lastProduct by viewModel.lastProduct.collectAsState()
    val starredProducts by viewModel.starredProducts.collectAsState()
    val allProducts by viewModel.allProducts.collectAsState()

    Box(
        Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.Black.copy(.9f))
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.size(0.dp))
            Header {
                onAddProductButtonClicked()
            }
            Spacer(modifier = Modifier.size(16.dp))
            LastProduct(lastProduct)
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                TitleText("Productos destacados")
            }
            StarProductsList(starredProducts)
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                TitleText("Todos los productos")
            }
            FlowRow(
                maxItemsInEachRow = 2,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (allProducts != null) {
                    allProducts?.let { list ->
                        list.forEach { product ->
                            product?.let {
                                Card(
                                    modifier = Modifier
                                        .widthIn(min = 100.dp)
                                        .weight(1f)
                                        .height(380.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
                                ) {
                                    Column(
                                        Modifier
                                            .fillMaxSize()
                                            .padding(16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.SpaceEvenly
                                    ) {
                                        AsyncImage(
                                            model = it.imageUri,
                                            modifier = Modifier.size(140.dp),
                                            contentDescription = "image product",
                                            contentScale = ContentScale.Crop
                                        )
                                        Box(
                                            Modifier.fillMaxWidth(),
                                            contentAlignment = Alignment.CenterStart
                                        ) {
                                            TitleText(it.name.orEmpty())
                                        }
                                        BodyText(it.description.orEmpty())
                                        SubtitleText("$${it.price}")

                                    }
                                }
                            }

                        }
                    }
                } else {
                    Card(
                        modifier = Modifier
                            .widthIn(min = 100.dp)
                            .weight(1f)
                            .height(380.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
                    ) {
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(140.dp)
                                    .background(Color.White)
                            )
                            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                                TitleText("Item name")
                            }
                            BodyText("This is a description for a normal simple item")
                            SubtitleText("$12.500")
                        }
                    }
                    Card(
                        modifier = Modifier
                            .widthIn(min = 100.dp)
                            .weight(1f)
                            .height(380.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
                    ) {
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(140.dp)
                                    .background(Color.White)
                            )
                            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                                TitleText("Item name")
                            }
                            BodyText("This is a description for a normal simple item")
                            SubtitleText("$12.500")
                        }
                    }
                    Card(
                        modifier = Modifier
                            .widthIn(min = 100.dp)
                            .weight(1f)
                            .height(380.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
                    ) {
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(140.dp)
                                    .background(Color.White)
                            )
                            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                                TitleText("Item name")
                            }
                            BodyText("This is a description for a normal simple item")
                            SubtitleText("$12.500")
                        }
                    }
                    Card(
                        modifier = Modifier
                            .widthIn(min = 100.dp)
                            .weight(1f)
                            .height(380.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
                    ) {
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(140.dp)
                                    .background(Color.White)
                            )
                            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                                TitleText("Item name")
                            }
                            BodyText("This is a description for a normal simple item")
                            SubtitleText("$12.500")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProductCardItem() {
    Card(
        modifier = Modifier
            .widthIn(min = 100.dp, max = 200.dp)
            .height(380.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .background(Color.White)
            )
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                TitleText("Item name")
            }
            BodyText("This is a description for a normal simple item")
            SubtitleText("$12.500")
        }
    }
}

@Composable
private fun StarProductsList(starredProducts: List<Product?>?) {

    if (starredProducts != null) {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            starredProducts.forEach { product ->
                product?.let {
                    item {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Card(
                                modifier = Modifier.size(140.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
                            ) {
                                AsyncImage(
                                    model = product.imageUri,
                                    modifier = Modifier.fillMaxSize(),
                                    contentDescription = "Product Image",
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Spacer(modifier = Modifier.size(4.dp))
                            SubtitleText(product.name.orEmpty())
                        }
                    }
                }
            }

        }
    } else {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(7) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        modifier = Modifier.size(140.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
                    ) {
                    }
                    Spacer(modifier = Modifier.size(4.dp))
                    SubtitleText("Item name")
                }
            }
        }
    }

}

@Composable
private fun Header(onAddProductButtonClicked: () -> Unit) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TitleText("Home")
        Spacer(modifier = Modifier.weight(.6f))
        TextButton(
            onClick = { onAddProductButtonClicked() }
        ) { BodyText("Añadir producto") }
    }
}

@Composable
private fun LastProduct(product: Product?) {
    if (product != null) {
        Card(
            modifier = Modifier
                .height(350.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = product.imageUri,
                    modifier = Modifier.size(160.dp),
                    contentDescription = "product image",
                    contentScale = ContentScale.Crop
                )
                product.name?.let { TitleText(it) }
                product.description?.let { BodyText(it) }
            }
        }
    } else {
        Card(
            modifier = Modifier
                .height(350.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    Modifier
                        .size(160.dp)
                        .background(Color.White)
                )
                TitleText("First Title")
                BodyText("This is a normal description")
            }
        }
    }

}

//
//data class UIState(
//
//)