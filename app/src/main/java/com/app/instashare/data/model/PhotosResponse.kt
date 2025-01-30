package com.app.instashare.data.model

data class PhotosResponse(
    val gif_count: Int,
    val gifs: List<String>,
    val http: List<String>,
    val image_count: Int,
    val images: List<String>
)