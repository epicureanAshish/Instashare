package com.app.instashare.data.repository

import com.app.instashare.data.api.ApiService
import com.app.instashare.data.model.PhotosResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(
    private val apiService: ApiService
){

    suspend fun getPhotos() : PhotosResponse? {
        val response= apiService.getPhotos()
        return if(response.isSuccessful)
            response.body()
        else
            PhotosResponse(
                gif_count = 0,
                gifs = emptyList(),
                http = emptyList(),
                images = emptyList(),
                image_count = 0
            )
    }

}