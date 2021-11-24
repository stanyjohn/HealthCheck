package com.sample.splashsample.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.splashsample.repository.HealthRepository

class HealthViewModalFactory constructor(private val healthRepository: HealthRepository): ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HealthViewModal::class.java)) {
            HealthViewModal(this.healthRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}