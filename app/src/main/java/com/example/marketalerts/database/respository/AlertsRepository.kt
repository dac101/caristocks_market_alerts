package com.example.marketalerts.database.respository

import com.example.marketalerts.database.AlertsDao
import com.example.marketalerts.database.AppDatabase
import com.example.marketalerts.database.entities.AlertsEntities

class AlertsRepository (private val dao: AlertsDao) {


    fun getAll() = dao.getAll()

//    fun getPlant(id: Int) = diseasesSymptomsDao.getById(id)
//
//    fun getSymptomsByDisease(disease: String) = diseasesSymptomsDao.getSymptomsByDisease(disease)
//
 suspend fun insert(item: AlertsEntities) {
       AppDatabase.databaseWriteExecutor.execute({ dao.insert(item) })
    }

}