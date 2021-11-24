package com.sample.splashsample.viewmodal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.splashsample.modal.HealthModal
import com.sample.splashsample.repository.HealthRepository
import kotlinx.coroutines.*

class HealthViewModal constructor(private val healthRepository: HealthRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    var job: Job? = null
    val healthList = MutableLiveData<HealthModal>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun getHealthAPIDetails(){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = healthRepository.getHealthCheckDetails()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    healthList.postValue(response.body())
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }
}