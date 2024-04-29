package com.example.bookshelf.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import com.example.bookshelf.ui.screens.queryScreen.QueryViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookshelf.R
import com.example.bookshelf.ui.screens.detailsScreen.DetailsScreen
import com.example.bookshelf.ui.screens.queryScreen.QueryScreen

enum class BookshelfScreens(@StringRes val title: Int){
    QueryScreen(title = R.string.search),
    DetailsScreen(title = R.string.details)
}

@Composable
fun BookshelfApp(
    viewModel: QueryViewModel = viewModel(factory = QueryViewModel.Factory),
    navController: NavHostController = rememberNavController()){
    Scaffold(
        modifier = Modifier
    ){
        innerPadding ->

        NavHost(
            navController = navController,
            startDestination = BookshelfScreens.QueryScreen.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = BookshelfScreens.QueryScreen.name){
                QueryScreen(
                    viewModel = viewModel,
                    onDetailsClick = {
                        viewModel.selectedBook = it
                        navController.navigate(BookshelfScreens.DetailsScreen.name)
                    },
                    retryAction = {
                        viewModel.getBooks()
                    }
                )
            }
            composable(route = BookshelfScreens.DetailsScreen.name){
                DetailsScreen(book = viewModel.selectedBook, modifier = Modifier)
            }
        }
    }
}