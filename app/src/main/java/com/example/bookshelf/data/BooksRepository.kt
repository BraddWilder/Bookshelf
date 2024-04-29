package com.example.bookshelf.data

import com.example.bookshelf.model.Book
import com.example.bookshelf.network.BooksApiService

interface BooksRepository {
    suspend fun getBooks(query: String): List<Book>?
}

class NetworkBooksRepository(private val bookApiService: BooksApiService): BooksRepository{
    override suspend fun getBooks(query: String): List<Book>?{
        return try{
            val res = bookApiService.getBooks(query = query)
            if(res.isSuccessful){
                res.body()?.items ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
}