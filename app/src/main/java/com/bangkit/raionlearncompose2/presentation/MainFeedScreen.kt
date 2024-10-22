package com.bangkit.raionlearncompose2.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit.raionlearncompose2.util.Resource

@Composable
fun MainFeedScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val productsState by viewModel.productsState.collectAsStateWithLifecycle(initialValue = Resource.Loading())

    LazyVerticalGrid(
        columns = GridCells
            .Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        when (productsState) {
            is Resource.Error -> {
                Toast.makeText(context, productsState.msg, Toast.LENGTH_SHORT).show()
            }

            is Resource.Loading -> {
                items(10) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 200.dp)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        )
                    }
                }
            }

            is Resource.Success -> {
                items(productsState.data?.size ?: 0) {
                    ProductCard(
                        productTitle = productsState.data?.get(it)?.title.toString(),
                        productImageUrl = productsState.data?.get(it)?.image.toString()
                    )
                }
            }
        }
    }
}