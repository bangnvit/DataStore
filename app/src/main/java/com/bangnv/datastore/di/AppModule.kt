package com.bangnv.datastore.di

import android.content.Context
import com.bangnv.datastore.model.datasource.MyPreferenceDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreferenceDataStore(@ApplicationContext context: Context): MyPreferenceDataStore {
        return MyPreferenceDataStore(context)
    }

}