package com.android.githubsearch.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.githubsearch.ui.composable.ErrorPage
import com.android.githubsearch.ui.composable.FullScreenProgress
import com.android.githubsearch.ui.composable.IdleState
import com.android.githubsearch.ui.composable.SearchComponent
import com.android.githubsearch.ui.composable.SearchList
import com.android.githubsearch.ui.theme.GithubSearchTheme
import com.android.githubsearch.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubSearchTheme {
                val searchViewModel by viewModels<SearchViewModel>()

                val viewState = searchViewModel.viewState.collectAsState()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        SearchComponent {
                            searchViewModel.searchUser(it)
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        when (viewState.value) {
                            is SearchViewModel.ViewState.Data -> {
                                val data = (viewState.value as SearchViewModel.ViewState.Data).data
                                SearchList(searchResult = data)
                            }
                            is SearchViewModel.ViewState.Error -> {
                                val errorMessage = (viewState.value as SearchViewModel.ViewState.Error).message
                                ErrorPage(message = errorMessage)
                            }
                            SearchViewModel.ViewState.Idle -> {
                                IdleState()
                            }
                            SearchViewModel.ViewState.Loading -> {
                                FullScreenProgress()
                            }
                        }
                    }
                }
            }
        }
    }
}
