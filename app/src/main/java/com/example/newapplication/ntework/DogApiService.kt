package com.example.newapplication.ntework

import com.example.newapplication.data.DogResponse
import com.example.newapplication.data.ImagesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiService {
    @GET("breeds/list/all")
    suspend fun getAllBreeds(): DogResponse

    @GET("breed/{breed}/images")
    suspend fun getImagesByBreed(@Path("breed") breed: String): ImagesResponse

    @GET("breed/{breed}/{subBreed}/images")
    suspend fun getImagesBySubBreed(
        @Path("breed") breed: String,
        @Path("subBreed") subBreed: String
    ): ImagesResponse
}
