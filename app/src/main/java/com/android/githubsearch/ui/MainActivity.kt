package com.android.githubsearch.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.android.githubsearch.ui.composable.SearchComponent
import com.android.githubsearch.ui.composable.SearchList
import com.android.githubsearch.ui.theme.GithubSearchTheme
import com.android.githubsearch.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val searchViewModel by viewModels<SearchViewModel>()
    @OptIn(ExperimentalCoilApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubSearchApp(searchViewModel)
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun GithubSearchApp(searchViewModel: SearchViewModel) {
    GithubSearchTheme {
        val data = searchViewModel.searchResult.collectAsLazyPagingItems()
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(Modifier.padding(16.dp)) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(modifier = Modifier.padding(16.dp), text = "Github Search", fontSize = 20.sp, fontWeight = FontWeight.Black)
                }
                SearchComponent {
                    searchViewModel.searchUser(it)
                }
                SearchList(searchResult = data)
            }
        }
    }
}
