package com.ksp.kspm.api

import com.google.gson.annotations.SerializedName


data class APIResponse<T>(
    @SerializedName("customMessage")
    val message: String = "",
    @SerializedName("errorCode")
    val errorCode: Int = 0,
    @SerializedName("statusCode")
    val status: Int = 0,
    @SerializedName("data")
    val data: T? = null
)
