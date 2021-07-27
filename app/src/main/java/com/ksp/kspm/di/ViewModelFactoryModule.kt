package com.ksp.kspm.di

import androidx.lifecycle.ViewModelProvider
import com.ksp.kspm.di.ViewModelFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewmodelfactory: ViewModelFactory): ViewModelProvider.Factory

     /* @Provides
      @Singleton
      fun viewModelFactory(providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {
          return ViewModelFactory(providerMap)
      }*/
}
