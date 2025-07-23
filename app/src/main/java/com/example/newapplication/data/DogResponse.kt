package com.example.newapplication.data

data class DogResponse(
    val message: Map<String, List<String>>,
    val status: String
)