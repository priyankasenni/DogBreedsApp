package com.example.newapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapplication.ntework.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogsViewModel : ViewModel() {
    private val _breeds = MutableStateFlow<List<String>>(emptyList())
    val breeds: StateFlow<List<String>> = _breeds

    private val _images = MutableStateFlow<List<String>>(emptyList())
    val images: StateFlow<List<String>> = _images

    fun loadBreeds() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getAllBreeds()
                val flatList = response.message.flatMap { (breed, subBreeds) ->
                    if (subBreeds.isEmpty()) listOf(breed)
                    else subBreeds.map { "$breed/$it" }
                }
                _breeds.value = flatList
            } catch (e: Exception) {
                _breeds.value = listOf("Error loading breeds")
            }
        }
    }

    fun loadImages(breed: String) {
        viewModelScope.launch {
            try {
                val breedParts = breed.split("/")
                val response = if (breedParts.size == 2) {
                    RetrofitInstance.api.getImagesBySubBreed(breedParts[0], breedParts[1])
                } else {
                    RetrofitInstance.api.getImagesByBreed(breedParts[0])
                }
                _images.value = response.message
            } catch (e: Exception) {
                _images.value = listOf()
            }
        }
    }
}
