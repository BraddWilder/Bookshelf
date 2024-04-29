package com.example.bookshelf.ui.screens.detailsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.Book

@Composable
fun DetailsScreen(
    book: Book,
    modifier: Modifier
    ){
    Surface(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()){
            Text(
                text = book.volumeInfo.title,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = getFormattedAuthors(book),
                style = MaterialTheme.typography.headlineSmall
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(book.volumeInfo.imageLinks?.thumbnail)
                    .crossfade(true)
                    .build(),
                error = painterResource(id = R.drawable.broken_image),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .aspectRatio(0.6f)
                    .padding(8.dp)
            )
            Text(
                text = book.volumeInfo.description
            )
        }
    }
}

fun getFormattedAuthors(book: Book) : String{
    var formattedAuthors = ""
    val authors = book.volumeInfo.authors

    when(authors.count()){
        2 -> formattedAuthors = "${authors[0]} & ${authors[1]}"
        1 -> formattedAuthors = authors[0]
        else -> for((index, author) in authors.withIndex()){
            if(index == authors.count() - 1)
                formattedAuthors += " & ${authors[index]}"
            else if(index == authors.count() -2)
                formattedAuthors += " $author"
            else if(index == 0)
                formattedAuthors += author
            else
                formattedAuthors += " $author,"
        }
    }

    return formattedAuthors
}