package com.android.githubsearch.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.android.githubsearch.ui.model.SearchItem

@Composable
fun SearchList(searchResult: List<SearchItem>) {
    LazyColumn {
        items(searchResult) { search ->
            SearchListItem(searchItem = search)
            Divider(Modifier.fillMaxWidth().height(1.dp), color = Color.Gray)
        }
    }
}
