package com.example.bookshelf.data

import com.example.bookshelf.network.BooksApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

interface AppContainer {
    val booksRepository: BooksRepository
}

class DefaultAppContainer: AppContainer{
    private val baseUrl = "https://www.googleapis.com/books/v1/"

//    private val retrofit: Retrofit = Retrofit.Builder()
//        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
//        .baseUrl(baseUrl)
//        .build()
//
//    private val retrofitService: BooksApiService by lazy {
//        retrofit.create(BooksApiService::class.java)
//    }
//    private val booksApiService: BooksApiService by lazy {
//        Retrofit.Builder()
//            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
//            .baseUrl(baseUrl).build().create()
    private val booksApiService: BooksApiService by lazy{
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl).build().create()
}

    override val booksRepository: BooksRepository by lazy {
        NetworkBooksRepository(booksApiService)
    }
}