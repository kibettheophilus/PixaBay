package dev.kibet.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.kibet.domain.models.Image
import dev.kibet.domain.usecases.GetImageDetails
import dev.kibet.domain.usecases.GetImages
import dev.kibet.domain.utils.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ImagesViewModel(
    private val imagesUseCase: GetImages,
    private val imageDetailsUseCase: GetImageDetails
) : ViewModel() {
    private val _imageStatus = MutableLiveData<Resource<List<Image>>>()
    val imageStatus: LiveData<Resource<List<Image>>> = _imageStatus

    private val _imageDetails = MutableLiveData<Resource<Image>>()
    val imageDetails: LiveData<Resource<Image>> = _imageDetails

    fun getImages(keyWord: String) {
        viewModelScope.launch {
            imagesUseCase.invoke(keyWord).onStart {
                _imageStatus.value = Resource.loading(null)
            }.catch {
                _imageStatus.value = Resource.error("Check your internet connection", null)
            }.collect {
                _imageStatus.value = Resource.success(it)
            }
        }
    }

    fun getImageDetails(id: Int) {
        viewModelScope.launch {
            imageDetailsUseCase.invoke(id).onStart {
                _imageDetails.value = Resource.loading(null)
            }.catch {
                _imageDetails.value = Resource.error(it.message.toString(), null)
            }.collect {
                _imageDetails.value = Resource.success(it)
            }
        }
    }
}
