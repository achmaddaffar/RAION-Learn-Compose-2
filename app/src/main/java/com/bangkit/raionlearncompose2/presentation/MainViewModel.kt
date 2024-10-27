package com.bangkit.raionlearncompose2.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.raionlearncompose2.data.remote.ApiConfig
import com.bangkit.raionlearncompose2.data.remote.response.ProductResponseItem
import com.bangkit.raionlearncompose2.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // Cold
    private val _products = Channel<Resource<List<ProductResponseItem>>>()
    val products = _products
        .receiveAsFlow()
        .onStart {
            fetchProducts()
        }

    // Hot
    private val _products2 = MutableSharedFlow<List<ProductResponseItem>>()
    val products2 = _products2.asSharedFlow()

    private fun fetchProducts() {
        viewModelScope.launch {
            flow {
                emit(Resource.Loading())
                try {
                    val response = ApiConfig.getApiService().getProducts()
                    if (response.isSuccessful)
                        emit(Resource.Success(response.body()))
                    else
                        emit(Resource.Error("error wak"))
                } catch (e: Exception) {
                    emit(Resource.Error("error wak"))
                }
            }.collect {
                _products.send(it)
            }
        }
    }
}