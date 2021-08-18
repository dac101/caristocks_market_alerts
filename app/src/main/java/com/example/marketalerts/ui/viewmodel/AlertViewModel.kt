package com.example.marketalerts.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.marketalerts.database.AppDatabase
import com.example.marketalerts.database.entities.AlertsEntities
import com.example.marketalerts.database.respository.AlertsRepository


/*This how you do parameters for kotlin*/
class AlertViewModel (application: Application): AndroidViewModel(application) {

    private val repository: AlertsRepository
     val alItem : LiveData<List<AlertsEntities>>

    init {
        val dao =   AppDatabase.getDatabase(application)!!.alertsDao()
        repository = AlertsRepository(dao)
        alItem = repository.getAll()
    }

    suspend fun insert(item: AlertsEntities) {
        repository.insert(item)
    }

}