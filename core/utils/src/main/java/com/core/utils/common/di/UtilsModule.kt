package com.core.utils.common.di

import android.content.Context
import com.core.utils.BuildConfig
import com.core.utils.common.DefaultDispatcherProvider
import com.core.utils.common.DispatcherProvider
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UtilsModule {

    @Provides
    @Singleton
    fun providePlacesClient(@ApplicationContext context: Context): PlacesClient {
        Places.initialize(context, BuildConfig.API_KEY)
        return Places.createClient(context)
    }

    @Provides
    fun provideDispatchers(): DispatcherProvider{
        return DefaultDispatcherProvider()
    }
}