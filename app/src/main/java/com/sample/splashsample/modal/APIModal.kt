package com.sample.splashsample.modal

data class HealthModal (

    val statusCode : Int,
    val success : Boolean,
    val data : Data
)

data class Data (

    val message : String,
    val health : List<Health>
)

data class Health (

    val name : String,
    val accessible : List<Accessible>
)

data class Accessible (

    val type : String,
    val success : Boolean,
    val message : String,
    val name : String,
)