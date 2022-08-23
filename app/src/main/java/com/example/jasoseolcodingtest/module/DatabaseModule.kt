package com.example.jasoseolcodingtest.module

import android.content.Context
import com.example.jasoseolcodingtest.domain.local.AppDatabase
import com.example.jasoseolcodingtest.domain.local.FavoriteDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = AppDatabase.getInstance(context)

    @Provides
    fun provideFavoriteDao(appDatabase: AppDatabase): FavoriteDAO {
        return appDatabase.favoriteDao()
    }
}