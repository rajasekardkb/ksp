package com.ksp.kspm.core.data

import android.content.Context
import com.ksp.kspm.api.ApiService
import javax.inject.Inject

class DataSource @Inject constructor(
    private val apiService: ApiService,
    applicationContext: Context
) :
    BaseDataSource(applicationContext) {


}