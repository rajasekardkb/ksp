package com.ksp.kspm.di

import android.app.Application
import com.ksp.kspm.App
import com.ksp.kspm.di.module.ActivityBindingModule
import com.ksp.kspm.di.module.AppModule
import com.ksp.kspm.di.module.FragmentBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        FragmentBindingModule::class,
        ViewModelFactoryModule::class,
    ]
)


interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}
