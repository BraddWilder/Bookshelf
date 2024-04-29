package com.example.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class Book (
    var id: String = "",
    var volumeInfo: VolumeInfo = VolumeInfo()
)

@Serializable
data class VolumeInfo(
    var title: String = "",
    var subtitle: String = "",
    var authors: List<String> = emptyList(),
    var publisher: String = "",
    var publisherDate: String = "",
    var description: String = "",
    var imageLinks: ImageLinks? = null
)

@Serializable
data class ImageLinks(
    var smallThumbnail: String,
    var thumbnail: String
)