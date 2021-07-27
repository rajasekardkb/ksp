package com.ksp.kspm.api

import com.ksp.kspm.core.data.CommonResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    companion object {
        const val DEV_ENDPOINT = ""
        const val STG_ENDPOINT = ""
        const val LIVE_ENDPOINT = ""
        const val DEV_ENDPOINT_BASE = ""
    }

    //Suggestions
    @POST("suggestionList")
    suspend fun getSuggestionList(@Body suggestionRequestModel: CommonResponseModel): Response<CommonResponseModel>

}