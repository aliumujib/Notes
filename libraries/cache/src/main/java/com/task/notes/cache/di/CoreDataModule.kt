package com.task.notes.cache.di

import android.content.Context
import androidx.room.Room
import com.task.notes.cache.db.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

const val DATABASE_NAME = "notes_app"

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NotesDBFactory

@Module
@InstallIn(SingletonComponent::class)
object BaseDataModule {

    @Provides
    @Singleton
    @NotesDBFactory
    fun provideDatabaseProvider(@ApplicationContext context: Context): NotesDatabase {
        return Room.databaseBuilder(context, NotesDatabase::class.java, DATABASE_NAME)
            .addMigrations() // TODO: Deal with migrations
            .fallbackToDestructiveMigration()
            .build()
    }

}
