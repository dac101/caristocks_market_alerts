package com.example.marketalerts.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.marketalerts.database.entities.AlertsEntities

@Dao
interface AlertsDao {
    @Query("SELECT * FROM alerts")
    fun getAll(): LiveData<List<AlertsEntities>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: AlertsEntities)

}