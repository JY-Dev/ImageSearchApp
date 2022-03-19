package com.jydev.imagesearchapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jydev.domain.model.ImageFeed
import com.jydev.domain.usecase.DeleteImageFeedLibraryFromUrlUseCase
import com.jydev.domain.usecase.GetImageFeedLibraryUseCase
import com.jydev.domain.usecase.InsertImageFeedLibraryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageFeedLibraryViewModel @Inject constructor(
    private val insertImageFeedLibraryUseCase: InsertImageFeedLibraryUseCase,
    private val deleteImageFeedLibraryFromUrlUseCase: DeleteImageFeedLibraryFromUrlUseCase,
    private val getImageFeedLibraryUseCase: GetImageFeedLibraryUseCase
) : ViewModel() {

    private val _imageFeedLibrary = MutableLiveData<List<ImageFeed>>()
    val imageFeed: LiveData<List<ImageFeed>> get() = _imageFeedLibrary

    private val imageFeedLibraryMap = hashMapOf<String,Boolean>()

    fun getImageFeedLibraryData() {
        viewModelScope.launch {
            val data = getImageFeedLibraryUseCase()
            data.forEach {
                imageFeedLibraryMap[it.url] = true
            }
            _imageFeedLibrary.value = data
        }
    }

    fun deleteImageFeedLibrary(url : String){
        viewModelScope.launch {
            imageFeedLibraryMap.remove(url)
            deleteImageFeedLibraryFromUrlUseCase(url)
            getImageFeedLibraryData()
        }
    }

    fun insertImageFeedLibrary(imageFeed: ImageFeed){
        viewModelScope.launch {
            imageFeedLibraryMap[imageFeed.url] = true
            insertImageFeedLibraryUseCase(imageFeed)
            getImageFeedLibraryData()
        }
    }

    fun isImageFeedInLibrary(url : String) : Boolean
        = imageFeedLibraryMap[url]?:false
}