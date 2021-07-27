package com.ksp.kspm.di.module

import com.ksp.kspm.MainActivity
import com.ksp.kspm.di.Scope.LoginScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @LoginScope
    @ContributesAndroidInjector()
    abstract fun mainActivity(): MainActivity

}