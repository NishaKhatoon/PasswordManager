package com.test.passwordmanagerapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [PasswordEntity::class], version = 2)
abstract class PasswordDatabase : RoomDatabase(){

    abstract fun passwordDao(): PasswordDao

    companion object {

       // @Volatile
        //private var INSTANCE: PasswordDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Example migration, adjust according to your schema changes
                database.execSQL("ALTER TABLE passwords ADD COLUMN new_column TEXT")
            }
        }

        /*fun getDatabase(context: Context): PasswordDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PasswordDatabase::class.java,
                    "password_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }*/
    }
}