package com.example.newapplication.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.newapplication.viewModel.DogsViewModel

@Composable
fun BreedImages(breed: String, viewModel: DogsViewModel) {
    val images = viewModel.images.collectAsState()

    LaunchedEffect(breed) {
        viewModel.loadImages(breed)
    }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(images.value) { imageUrl ->
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(vertical = 8.dp)
            )
        }
    }
}
