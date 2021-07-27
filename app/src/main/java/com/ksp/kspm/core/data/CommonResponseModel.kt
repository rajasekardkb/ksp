package com.ksp.kspm.core.data

import com.google.gson.annotations.SerializedName

data class CommonResponseModel(
    @SerializedName("data")
    val data: Any? = null,
    @SerializedName("customMessage")
    val customMessage: String? = "",
    @SerializedName("statusCode")
    val statusCode: Int = 0
)


