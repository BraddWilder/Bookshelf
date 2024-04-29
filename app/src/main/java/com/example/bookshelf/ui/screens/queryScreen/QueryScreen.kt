package com.example.bookshelf.ui.screens.queryScreen

import android.graphics.fonts.FontStyle
import android.util.Log
import android.view.KeyEvent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.model.Book
import com.example.bookshelf.ui.components.ErrorScreen
import com.example.bookshelf.ui.components.LoadingScreen
import com.example.bookshelf.ui.screens.detailsScreen.DetailsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QueryScreen(viewModel: QueryViewModel,
                onDetailsClick: (Book) -> Unit,
                retryAction: () -> Unit) {
    val uiState = viewModel.uiState
    Column {
        OutlinedTextField(
            value = viewModel.query,
            onValueChange = {viewModel.query = it},
            singleLine = true,
            placeholder = {
                "Do the typy type"
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    viewModel.getBooks()
                }
            ),
            modifier = Modifier
                .onKeyEvent { e ->
                    if (e.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                        viewModel.getBooks()
                    }
                    false
                }
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
        )

        if(viewModel.isSearchStated) {
            when (uiState) {
                is QueryUiState.Loading -> LoadingScreen()
                is QueryUiState.Success -> BookList(
                    uiState.books, onDetailsClick
                )

                is QueryUiState.Error -> ErrorScreen(retryAction = retryAction)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridItem(
    book: Book,
    onDetailsClick: (Book) -> Unit,
    modifier: Modifier
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(8.dp),
        onClick = {
            onDetailsClick(book)
            Log.d("QueryScreen - Grid Item","${book.volumeInfo.title} clicked")
            Log.d("QueryScreen - Grid Item","Authors: ${book.volumeInfo.authors.toString()}")
        }) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(book.volumeInfo.imageLinks?.thumbnail)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier.aspectRatio(.6f),
            contentScale = ContentScale.FillBounds)

        Text(
            text = book.volumeInfo.title,
            modifier = Modifier
                .padding(8.dp),
            fontWeight = FontWeight.Bold
            )

    }
}

@Composable
fun BookList(
    books: List<Book>,
    onDetailsClick: (Book) -> Unit
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = Modifier.padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(items = books, key = {book -> book.id}){
            book -> GridItem(
            book = book,
            onDetailsClick = onDetailsClick,
            modifier = Modifier)
        }
    }
}