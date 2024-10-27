package com.bangkit.raionlearncompose2.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import com.bangkit.raionlearncompose2.util.Resource

@Composable
fun DetailProductScreen(
    productId: String,
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel<DetailViewModel>()
    val detail by viewModel.detail.collectAsState(initial = Resource.Loading())

    LaunchedEffect(Unit) {
        viewModel.fetchProductDetail(productId)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        SubcomposeAsyncImage(
            model = detail.data?.image,
            contentDescription = "Product Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 200.dp),
            loading = {
                CircularProgressIndicator()
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = detail.data?.title ?: "ERROR")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = detail.data?.description ?: "ERROR")
    }
}