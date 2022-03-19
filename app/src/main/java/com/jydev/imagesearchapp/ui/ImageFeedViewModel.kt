package com.jydev.imagesearchapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.jydev.domain.model.ImageFeed
import com.jydev.domain.usecase.GetImageFeedPagingDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageFeedViewModel @Inject constructor(private val getImageFeedPagingDataUseCase: GetImageFeedPagingDataUseCase): ViewModel() {

    private val _imageFeedPagingData = MutableLiveData<PagingData<ImageFeed>>()
    val imageFeedPagingData : LiveData<PagingData<ImageFeed>> get() = _imageFeedPagingData

    fun getImageFeedPagingData(query : String){
        viewModelScope.launch {
            getImageFeedPagingDataUseCase(query,30).collect {
                _imageFeedPagingData.value = it
            }
        }
    }
}