package com.android.githubsearch.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.githubsearch.ui.model.SearchItem

@Composable
fun SearchListItem(searchItem: SearchItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            Modifier
                .padding(16.dp)
        ) {
            Text(text = searchItem.login, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = searchItem.type, fontSize = 14.sp)
        }
    }
}
