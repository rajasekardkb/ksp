package com.ksp.kspm.di

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class GoWhereAPI

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class GoWhereNonApi

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CoroutineScopeIO
