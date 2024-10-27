package com.bangkit.raionlearncompose2.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bangkit.raionlearncompose2.presentation.ui.theme.RaionLearnCompose2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RaionLearnCompose2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "main_feed"
                    ) {
                        composable("main_feed") {
                            MainFeedScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )
                        }

                        composable("detail/{productId}") { navStack ->
                            val id = navStack.arguments?.getString("productId")
                            DetailProductScreen(
                                productId = id ?: "0",
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        composable("settings") {

                        }
                    }
                }
            }
        }
    }
}