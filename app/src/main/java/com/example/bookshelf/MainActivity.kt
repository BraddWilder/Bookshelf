package com.example.bookshelf

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.ui.BookshelfApp
import com.example.bookshelf.ui.screens.queryScreen.BookList
import com.example.bookshelf.ui.screens.queryScreen.QueryScreen
import com.example.bookshelf.ui.screens.queryScreen.QueryUiState
import com.example.bookshelf.ui.screens.queryScreen.QueryViewModel
import com.example.bookshelf.ui.theme.BookshelfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookshelfTheme {
                // A surface container using the 'background' color from the theme
                BookshelfApp()
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    val viewModel: QueryViewModel = viewModel(factory = QueryViewModel.Factory)
//                    QueryScreen(viewModel = viewModel)
//                }
            }
        }
    }
}