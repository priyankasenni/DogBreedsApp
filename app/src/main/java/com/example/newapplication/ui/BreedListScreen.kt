package com.example.newapplication.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newapplication.viewModel.DogsViewModel

@Composable
fun BreedList(navController: NavController, dogsViewModel: DogsViewModel) {
    val breedList = dogsViewModel.breeds.collectAsState()

    LaunchedEffect(Unit) {
        dogsViewModel.loadBreeds()
    }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(breedList.value) { breed ->
            Text(
                text = breed,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("breedImages/${breed}")
                    }
                    .padding(12.dp)
            )
        }
    }
}
