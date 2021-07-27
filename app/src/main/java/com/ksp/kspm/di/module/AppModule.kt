package com.ksp.kspm.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ksp.kspm.api.ApiService
import com.ksp.kspm.api.AuthInterceptor
import com.ksp.kspm.core.data.DataSource
import com.ksp.kspm.data.preferences.StringPref
import com.ksp.kspm.di.GoWhereAPI
import com.ksp.kspm.di.GoWhereNonApi
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import timber.log.Timber
import javax.inject.Named
import javax.inject.Singleton

@Module(
    includes = [
        ViewModelModule::class,
    ]
)


class AppModule {
    @Provides
    fun provideGoWhereApiService(
        @GoWhereNonApi okhttpClient: OkHttpClient,
        @GoWhereAPI okhttpthis: OkHttpClient,
        @Named(PreferenceModule.AUTH_TOKEN) authToken: StringPref,
        converterFactory: GsonConverterFactory, converterFactory1: ScalarsConverterFactory,
    ) = if (authToken.getValue().isEmpty()) {

        Timber.d("Access Token *#*# empty")


        provideService(
            okhttpClient,
            converterFactory, converterFactory1,
            ApiService::class.java
        )
    } else {

        Timber.d("Access Token *#*# ${authToken.getValue()}")

        provideService(okhttpthis, converterFactory, converterFactory1, ApiService::class.java)
    }


    @GoWhereAPI
    @Provides
    fun providePrivateOkHttpClient(
        @GoWhereNonApi upstreamClient: OkHttpClient,
        @Named(PreferenceModule.AUTH_TOKEN) accessToken: StringPref
    ): OkHttpClient {
        return upstreamClient.newBuilder()
            .addInterceptor(AuthInterceptor(accessToken.getValue())).build()
    }


    @Singleton
    @Provides
    fun provideSharedPreference(app: Application): SharedPreferences =
        app.getSharedPreferences(PreferenceModule.PREF_NAME, Context.MODE_PRIVATE)

    private fun createRetrofit(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
        converterFactory1: ScalarsConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiService.DEV_ENDPOINT)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .addConverterFactory(converterFactory1)
            .build()
    }


    @Provides
    fun provideDataSource(apiService: ApiService, context: Context) =
        DataSource(apiService, context)

    private fun <T> provideService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
        converterFactory1: ScalarsConverterFactory,
        clazz: Class<T>
    ): T {
        return createRetrofit(okhttpClient, converterFactory, converterFactory1).create(clazz)
    }

    @Provides
    @Singleton
    fun provideIOCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
