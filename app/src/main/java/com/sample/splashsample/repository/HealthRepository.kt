package com.sample.splashsample.repository

import com.sample.splashsample.api.RetrofitService

class HealthRepository constructor(private val retrofitService: RetrofitService) {
    suspend fun getHealthCheckDetails() = retrofitService.getHealthDetails()
}