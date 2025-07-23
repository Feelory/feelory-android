package com.feelory.feelory.feature.mypage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyPageRoute() {
    MyPageScreen()
}

@Composable
private fun MyPageScreen(modifier: Modifier = Modifier) {
    Text("MyPageScreen")
}
