package com.example.marketalerts.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.marketalerts.database.entities.AlertsEntities
import java.util.concurrent.Executors

@Database(entities = [AlertsEntities::class], version = 0, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun alertsDao(): AlertsDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS)


        fun getDatabase(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(
                    this
                ) {
                    instance =
                        Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "marketalerts_database"
                        )
                            .build()
                }
            }
            return instance
        }

    }
}
