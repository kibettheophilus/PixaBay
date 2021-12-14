package dev.kibet.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.kibet.domain.models.Image
import dev.kibet.domain.usecases.GetImagesUseCase
import dev.kibet.domain.utils.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ImagesViewModel(
    private val imagesUseCase: GetImagesUseCase
) : ViewModel() {
    private val _imageStatus = MutableLiveData<Resource<List<Image>>>()
    val imageStatus: LiveData<Resource<List<Image>>> = _imageStatus

    init {
        getImages()
    }
    fun getImages() {
        viewModelScope.launch {
            imagesUseCase.invoke("dog").onStart {
                _imageStatus.value = Resource.loading(null)
            }.catch {
                _imageStatus.value = Resource.error(it.message.toString(), null)
            }.collect {
                _imageStatus.value = Resource.success(it)
            }
        }
    }
}
