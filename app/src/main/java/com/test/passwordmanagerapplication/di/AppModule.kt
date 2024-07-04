package com.test.passwordmanagerapplication.di

import android.content.Context
import androidx.room.Room
import com.test.passwordmanagerapplication.data.PasswordDao
import com.test.passwordmanagerapplication.data.PasswordDatabase
import com.test.passwordmanagerapplication.data.repositories.PasswordRepository
import com.test.passwordmanagerapplication.data.repositories.PasswordRepositoryImpl
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
    fun provideDatabase(@ApplicationContext appContext: Context): PasswordDatabase {
        return Room.databaseBuilder(
            appContext,
            PasswordDatabase::class.java,
            "password_database"
        ).addMigrations(PasswordDatabase.MIGRATION_1_2)
            .build()
    }

    @Provides
    fun providePasswordDao(database: PasswordDatabase): PasswordDao {
        return database.passwordDao()
    }

    @Provides
    @Singleton
    fun providePasswordRepository(passwordDao: PasswordDao): PasswordRepository {
        return PasswordRepositoryImpl(passwordDao)
    }

}