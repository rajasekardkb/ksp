package com.ksp.kspm.base.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ksp.kspm.api.APIResponse
import com.ksp.kspm.core.data.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
open class BaseViewModel(val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

    val apiRequestInProgress = MutableLiveData<Boolean>()
    val title = MutableLiveData<String>()

    init {
        apiRequestInProgress.postValue( false)
    }

    fun <T> getDataResult(call: suspend () -> APIResponse<T>) = flow {
        val response = call()
        if (response.status == 200) {
            Timber.d("Api success")
            val result = Result.success(response.data)
            result.message = response.message
            emit(result)
        } else {
            Timber.e("Api error ${response.message}")
            emit(Result.error<T>(response.message))
        }
    }.onStart {
        emit(Result.loading<T>())
    }.flowOn(dispatcher)
}

