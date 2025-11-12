package com.example.supermarketapp.ui.generics

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TitleText(text:String, color:Color = Color.White) {
    Text(text, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = color)
}

@Composable
fun SubtitleText(text:String, color:Color = Color.White) {
    Text(text, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = color)
}



@Composable
fun BodyText(text:String, color:Color = Color.White) {
    Text(text, fontSize = 14.sp, fontWeight = FontWeight.Normal, color = color)
}
