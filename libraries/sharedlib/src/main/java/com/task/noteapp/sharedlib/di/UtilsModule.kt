package com.task.noteapp.sharedlib.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    fun providesCoroutineScope(): CoroutineScope? {
        return null
    }

}
