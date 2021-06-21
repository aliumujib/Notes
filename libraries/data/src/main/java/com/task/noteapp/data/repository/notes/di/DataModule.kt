package com.task.noteapp.data.repository.notes.di

import com.task.noteapp.data.repository.notes.NotesRepositoryImpl
import com.task.notes.cache.dao.NotesDao
import com.task.notes.cache.db.NotesDatabase
import com.task.notes.repository.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindNotesRepository(repository: NotesRepositoryImpl): NotesRepository
}

@Module
@InstallIn(SingletonComponent::class)
object Providers {

    @Provides
    @Singleton
    fun provideNotesDao(database: NotesDatabase): NotesDao {
        return database.notesDao()
    }
}
