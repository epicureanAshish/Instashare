package com.app.instashare.data.api

import com.app.instashare.data.model.PhotosResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    suspend fun getPhotos(): Response<PhotosResponse>
}
