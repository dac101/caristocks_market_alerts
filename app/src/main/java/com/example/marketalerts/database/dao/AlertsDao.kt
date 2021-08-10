package com.example.marketalerts.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.marketalerts.database.entities.AlertsEntities

@Dao
interface AlertsDao {
    @Query("SELECT * FROM alerts")
    fun getAll(): List<AlertsEntities>

    @Query("SELECT * FROM alerts WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<AlertsEntities>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): AlertsEntities


}