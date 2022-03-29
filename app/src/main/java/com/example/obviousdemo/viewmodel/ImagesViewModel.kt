package com.example.obviousdemo.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.obviousdemo.api.Resource
import com.example.obviousdemo.data.ImageRemoteSource
import com.example.obviousdemo.model.ResponseImages
import com.example.obviousdemo.model.ResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@VisibleForTesting
@HiltViewModel
class ImagesViewModel @Inject constructor(val imageRemoteSource: ImageRemoteSource) : ViewModel() {

    private val listImagesResponse = MutableLiveData<Resource<ResponseImages?>>()

    private val imageItem = MutableLiveData<ResponseItem?>()

    val _listImagesResponse: LiveData<Resource<ResponseImages?>>
        get() = listImagesResponse

    val _imageItem: LiveData<ResponseItem?>
        get() = imageItem

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        listImagesResponse.postValue(Resource.error("Something Went Wrong"))
    }

    fun getImagesData(){
        viewModelScope.launch {
            listImagesResponse.postValue(Resource.loading(null))
            val responseImages = imageRemoteSource.getImages()
            listImagesResponse.postValue(Resource.sucess(responseImages))
        }
    }

    fun getImageDetailsFromIndex(index : Int){
        viewModelScope.launch {
            imageItem.postValue(_listImagesResponse.value?.data?.response?.get(index))
        }
    }
}