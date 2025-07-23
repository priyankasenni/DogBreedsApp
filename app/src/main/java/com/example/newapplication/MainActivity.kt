package com.example.newapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newapplication.ui.BreedImages
import com.example.newapplication.ui.BreedList
import com.example.newapplication.ui.theme.NewApplicationTheme
import com.example.newapplication.viewModel.DogsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewApplicationTheme {
                val navController = rememberNavController()
                val viewModel = androidx.lifecycle.viewmodel.compose.viewModel<DogsViewModel>()

                NavHost(navController, startDestination = "breedList") {
                    composable("breedList") {
                        BreedList(navController, viewModel)
                    }
                    composable(
                        route = "breedImages/{breed}",
                        arguments = listOf(navArgument("breed") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val breed = backStackEntry.arguments?.getString("breed") ?: ""
                        BreedImages(breed, viewModel)
                    }
                }
            }
        }
    }
}