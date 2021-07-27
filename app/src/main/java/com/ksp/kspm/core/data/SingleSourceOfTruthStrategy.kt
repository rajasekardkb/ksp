@file:Suppress("UNCHECKED_CAST")

package com.ksp.kspm.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import timber.log.Timber

/**
 * The database serves as the single source of truth.
 * Therefore UI can receive data updates from database only.
 * Function notify UI about:
 * [Result.Status.SUCCESS] - with data from database
 * [Result.Status.ERROR] - if error has occurred from any source
 * [Result.Status.LOADING]
 */

//Network call, Storing to database and returning data from database
fun <T, A> resultLiveData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Result<T>> =
        liveData(Dispatchers.IO) {
            emit(Result.loading<T>())
            val source = databaseQuery.invoke().map {
                Result.success(it)
            }
            emitSource(source)
            val responseStatus = networkCall.invoke()
            if (responseStatus.status == Result.Status.SUCCESS) {
                saveCallResult(responseStatus.data!!)
            } else if (responseStatus.status == Result.Status.ERROR) {
                emit(Result.error<T>(responseStatus.message!!))
                emitSource(source)
            }
        }

@Deprecated(message = "Use flow instead", level = DeprecationLevel.WARNING)
fun <T> resultAPILiveData(networkCall: suspend () -> Result<T>): LiveData<Result<T>> =
        liveData(Dispatchers.IO) {
            emit(Result.loading<T>())
            val responseLiveData = MutableLiveData<Result<T>>()
            val responseStatus = networkCall.invoke()
            responseLiveData.postValue(responseStatus)
            if (responseStatus.status == Result.Status.SUCCESS) {
                emitSource(responseLiveData)
            } else emit(Result.error<T>(responseStatus.message!!))
        }

@Deprecated(message = "Use flow instead", level = DeprecationLevel.WARNING)
fun <U> resultAPILiveData2(networkCall: suspend () -> Result<U>): LiveData<Result<U>> =
        liveData(Dispatchers.IO) {
            try {
                Timber.d("Api initiated")
                emit(Result.loading<U>())
                val responseData = networkCall.invoke()
                if (responseData.status == Result.Status.SUCCESS) {
                    Timber.d("Api success")
                    emit(Result.success<U>(responseData.data!!))
                } else {
                    Timber.d("Api failed")
                    emit(Result.error<U>(responseData.message!!))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

fun <U> resultAPIFlow(networkCall: suspend () -> Result<U>) = flow {
    val responseData = networkCall.invoke()
    if (responseData.status == Result.Status.SUCCESS) {
        Timber.d("Api success")
        emit(Result.success<U>(responseData.data!!))
    } else {
        Timber.d("Api failed")
        emit(Result.error<U>(responseData.message!!))
    }
}.onStart {
    emit(Result.loading())
}.flowOn(Dispatchers.IO)

fun Job?.cancelIfActive() {
    if (this?.isActive == true) {
        cancel()
    }
}

