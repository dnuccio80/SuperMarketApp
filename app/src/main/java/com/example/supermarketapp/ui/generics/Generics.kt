package com.example.supermarketapp.ui.generics

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly

@Composable
fun TitleText(text: String, color: Color = Color.White) {
    Text(text, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = color)
}

@Composable
fun SubtitleText(text: String, color: Color = Color.White) {
    Text(text, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = color)
}

@Composable
fun BodyText(text: String, color: Color = Color.White) {
    Text(text, fontSize = 14.sp, fontWeight = FontWeight.Normal, color = color)
}

@Composable
fun TextFieldItem(
    value: String,
    label: String,
    isInt: Boolean = false,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = {
            if (isInt) {
                if (!it.isDigitsOnly()) return@TextField
                onValueChange(it)
            } else {
                onValueChange(it)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.DarkGray.copy(alpha = .2f),
            unfocusedContainerColor = Color.Transparent,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        ),
        singleLine = true,
        keyboardOptions = if (isInt) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions.Default,
        maxLines = 1,
        label = { BodyText(label) }
    )
}