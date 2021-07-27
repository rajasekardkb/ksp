package com.ksp.kspm.core.data

import android.content.Context
import android.content.Intent
import com.ksp.kspm.api.APIResponse
import com.google.gson.Gson
import com.ksp.kspm.MainActivity
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber

/**
 * Abstract Base com.torvis.gowhereuser.home.core.domain.restaurantDetails.Data source class with error handling
 */
abstract class BaseDataSource(val context: Context) {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    return Result.success(body)
            }
            return error(" ${response.errorBody()!!.string()}")
        } catch (e: Exception) {
            Timber.d("Error 1")
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        Timber.e(message)
        return Result.error(message)
    }

    private fun <T> error_(message: String): Result<T> {
        Timber.e(message)
        return Result.error(
            try {

                if (JSONObject(message).getInt("statusCode") == 401) {
                    handleBadToken()
                    JSONObject(message).getString("customMessage")
                } else {
                    JSONObject(message).getString("customMessage")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                message
            }
        )
    }

    private fun handleBadToken() {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    suspend fun <T : APIResponse<*>> getAPIResponse(call: suspend () -> Response<T>): T {
        try {
            val response = call()
            if (response.isSuccessful) {
                return response.body() as T
            }

            if (response.body() != null)
                return response.body() as T

            return parseErrorBody(response.code(), response.errorBody()) as T
        } catch (e: Exception) {
            Timber.e("Error ${e.localizedMessage}")
            return error(500, e.message ?: e.toString()) as T
        }
    }

    private fun  parseErrorBody(code: Int, errorBody: ResponseBody?): APIResponse<*> {
        return if (errorBody != null) {
            try {
                Gson().fromJson(errorBody.string(), APIResponse::class.java)
            } catch (ex: Exception) {
                error(code, errorBody.string())
            }
        } else {
            error(code, "Something went wrong!")
        }
    }

    private fun error(code: Int, error: String): APIResponse<*> {
        return APIResponse<Any>(status = code, message = error)
    }
}

