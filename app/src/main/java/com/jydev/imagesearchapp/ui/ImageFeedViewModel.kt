package com.jydev.imagesearchapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.jydev.domain.model.ImageThumbnail
import com.jydev.domain.usecase.GetImageThumbnailPagingDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageFeedViewModel @Inject constructor(private val getImageThumbnailPagingDataUseCase: GetImageThumbnailPagingDataUseCase): ViewModel() {

    private val _imageThumbnailPagingData = MutableLiveData<PagingData<ImageThumbnail>>()
    val imageThumbnailPagingData : LiveData<PagingData<ImageThumbnail>> get() = _imageThumbnailPagingData

    fun getImageThumbnailPagingData(query : String){
        viewModelScope.launch {
            getImageThumbnailPagingDataUseCase(query,30).collect {
                _imageThumbnailPagingData.value = it
            }
        }
    }
}