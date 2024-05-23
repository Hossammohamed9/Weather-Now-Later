package com.core.dispatchers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DispatchersModule {

    @Provides
    fun provideDispatchers(): DispatcherProvider {
        return DefaultDispatcherProvider()
    }

}