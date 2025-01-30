package com.app.instashare.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.instashare.data.model.PhotosResponse
import com.app.instashare.data.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    val photos = mutableStateOf(PhotosResponse(
        gif_count = 0,
        gifs = emptyList(),
        http = emptyList(),
        images = emptyList(),
        image_count = 0
    ))

    init {
        getPhotos()
    }

    private fun getPhotos(){
        viewModelScope.launch {
            try {
                photos.value = apiRepository.getPhotos()!!
                Log.i("api","${photos.value}")
            }catch (error: Exception){
                Log.i("api error","$error")
            }
        }
    }
}