package com.bangkit.raionlearncompose2.presentation

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

@Composable
fun ProductCard(
    productTitle: String,
    productImageUrl: String,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = {

        },
        modifier = modifier
    ) {
        SubcomposeAsyncImage(
            model = productImageUrl,
            contentDescription = "Product Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth(),
            loading = {
                CircularProgressIndicator()
            }
        )
        Text(
            text = productTitle,
            modifier = Modifier.padding(12.dp)
        )
    }
}