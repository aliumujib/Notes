package com.task.noteapp.data.repository.notes.di

import android.content.Context
import androidx.room.Room
import com.task.notes.cache.db.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val DATABASE_NAME = "notes_app"

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideDatabaseProvider(@ApplicationContext context: Context): NotesDatabase {
        return Room.databaseBuilder(context, NotesDatabase::class.java, DATABASE_NAME)
            .addMigrations() // TODO: Deal with migrations
            .fallbackToDestructiveMigration()
            .build()
    }
}
