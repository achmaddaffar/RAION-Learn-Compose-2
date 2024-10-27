package com.bangkit.raionlearncompose2.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bangkit.raionlearncompose2.util.Resource

@Composable
fun MainFeedScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val viewModel = viewModel<MainViewModel>()
    val products by viewModel.products.collectAsState(initial = Resource.Loading())

    LazyVerticalGrid(
        columns = GridCells
            .Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        when (products) {
            is Resource.Error -> {
                Toast.makeText(
                    context,
                    products.msg,
                    Toast.LENGTH_SHORT
                ).show()
            }

            is Resource.Loading -> {
                items(10) {
                    CircularProgressIndicator()
                }
            }

            is Resource.Success -> {
                items(products.data?.size ?: 0) {
                    ProductCard(
                        productTitle = products.data?.get(it)?.title ?: "ERROR",
                        productImageUrl = products.data?.get(it)?.image ?: "ERROR",
                        onProductClick =  {
                            navController.navigate("detail/${products.data?.get(it)?.id}")
                        }
                    )
                }
            }
        }
//        items(5) {
//            ProductCard(
//                productTitle = "product $it",
//                productImageUrl = "https://cdn.britannica.com/22/187222-050-07B17FB6/apples-on-a-tree-branch.jpg"
//            )
//        }
    }
}