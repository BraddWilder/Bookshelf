package com.example.bookshelf.ui.screens.queryScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.BooksRepository
import com.example.bookshelf.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface QueryUiState{
    data class Success(val books: List<Book>): QueryUiState
    object Error: QueryUiState
    object Loading: QueryUiState
}

class QueryViewModel (private val booksRepository: BooksRepository) : ViewModel() {
    var uiState: QueryUiState by mutableStateOf(QueryUiState.Loading)
        private set
    var query by mutableStateOf("")
    var selectedBook by mutableStateOf(Book())
    var isSearchStated by mutableStateOf(false)




    fun getBooks(){
        isSearchStated = true
        viewModelScope.launch {
            uiState = QueryUiState.Loading
            uiState = try{
                val books = booksRepository.getBooks(query = query)
                if(books == null){
                    QueryUiState.Error
                } else if(books.isEmpty()){
                    QueryUiState.Success(emptyList())
                } else {
                    QueryUiState.Success(alterThumbnails(books))
                }
            } catch (e: Exception){
                QueryUiState.Error
            }
        }
    }

    suspend fun alterThumbnails(books: List<Book>): List<Book>{
        var alteredList: List<Book> = books
        for(book:Book in alteredList){
            book.volumeInfo.imageLinks?.thumbnail =
                book.volumeInfo.imageLinks?.thumbnail?.replace("http", "https").toString()
        }
        return alteredList
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BookshelfApplication)
                val booksRepository = application.container.booksRepository
                QueryViewModel(booksRepository)
            }
        }
    }
}