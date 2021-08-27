package com.example.marketalerts.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "alerts")
data class AlertsEntities (
        @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "Symbol") val symbol: String?,
    @ColumnInfo(name = "price") val price: String? = null,
    @ColumnInfo(name = "Date") val date: String? = null

)