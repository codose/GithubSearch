package com.android.githubsearch.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.android.githubsearch.ui.model.SearchItem

@ExperimentalCoilApi
@Composable
fun SearchListItem(searchItem: SearchItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(4.dp))
            Image(modifier = Modifier.size(48.dp).clip(CircleShape), painter = rememberImagePainter(searchItem.avatarUrl), contentDescription = "Avatar Image")
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                Modifier
                    .padding(16.dp)
            ) {
                Text(text = searchItem.login, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = searchItem.type, fontSize = 14.sp)
            }
        }
    }
}
