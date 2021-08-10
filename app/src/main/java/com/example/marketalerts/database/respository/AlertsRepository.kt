package com.example.marketalerts.database.respository

import com.example.marketalerts.database.AlertsDao
import com.example.marketalerts.database.AppDatabase
import com.example.marketalerts.database.entities.AlertsEntities

class DiseasesSymptomsRepository (private val diseasesSymptomsDao: AlertsDao) {


    fun getAll() = diseasesSymptomsDao.getAll()

//    fun getPlant(id: Int) = diseasesSymptomsDao.getById(id)
//
//    fun getSymptomsByDisease(disease: String) = diseasesSymptomsDao.getSymptomsByDisease(disease)
//
//    suspend fun insert(item: AlertsEntities) {
//        AppDatabase.databaseWriteExecutor.execute({ diseasesSymptomsDao.insert(item) })
//    }

}