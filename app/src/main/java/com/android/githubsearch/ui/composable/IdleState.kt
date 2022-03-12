package com.android.githubsearch.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun IdleState(modifier: Modifier) {
    Box(modifier, contentAlignment = Alignment.Center) {
        Text("Search for github users", fontSize = 16.sp)
    }
}
