package com.bangkit.raionlearncompose2.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.raionlearncompose2.data.remote.ApiConfig
import com.bangkit.raionlearncompose2.data.remote.response.ProductResponseItem
import com.bangkit.raionlearncompose2.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val _detail = Channel<Resource<ProductResponseItem>>()
    val detail = _detail.receiveAsFlow()

    fun fetchProductDetail(id: String) {
        viewModelScope.launch {
            flow {
                emit(Resource.Loading())
                try {
                    val response = ApiConfig.getApiService().getProductDetail(id)
                    if (response.isSuccessful)
                        emit(Resource.Success(response.body()))
                    else
                        emit(Resource.Error("ERROR CUYH"))
                } catch (e: Exception) {
                    emit(Resource.Error("ERROR CUYH"))
                }
            }.collect {
                _detail.send(it)
            }
        }
    }
}