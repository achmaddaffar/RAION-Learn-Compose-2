package com.bangkit.raionlearncompose2.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.raionlearncompose2.data.remote.api.ApiConfig
import com.bangkit.raionlearncompose2.data.remote.response.ProductResponseItem
import com.bangkit.raionlearncompose2.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _productsState = Channel<Resource<List<ProductResponseItem>>>()
    val productsState = _productsState
        .consumeAsFlow()
        .onStart {
            getBreweries()
        }

    private fun getBreweries() {
        viewModelScope.launch {
            flow {
                emit(Resource.Loading())
                try {
                    val response = ApiConfig.getService().getBreweries()
                    if (response.isSuccessful)
                        emit(Resource.Success(response.body()))
                    else emit(Resource.Error(response.message()))
                } catch (e: Exception) {
                    emit(Resource.Error(e.message))
                }
            }
                .flowOn(Dispatchers.IO)
                .collect { _productsState.send(it) }
        }
    }
}