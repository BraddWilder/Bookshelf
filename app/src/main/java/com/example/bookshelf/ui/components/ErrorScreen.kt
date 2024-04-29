package com.example.bookshelf.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bookshelf.R

@Composable
fun ErrorScreen(
    retryAction: () -> Unit
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_internet),
            contentDescription = "",
            modifier = Modifier.size(120.dp)
                .padding(8.dp)
        )
        Text(
            text = "There was an error with your search",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(8.dp)
        )
        Button(
            onClick = retryAction,
            modifier = Modifier.padding(8.dp)
        ){
            Text(
                text ="Retry",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}