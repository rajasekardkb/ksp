package com.ksp.kspm.di.module

import com.ksp.kspm.api.ApiService
import com.ksp.kspm.di.Scope.LoginScope
import dagger.Module
import dagger.Provides

import retrofit2.Retrofit

@Module
class ApiModule {
    @LoginScope
    @Provides
    fun provideMainApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}