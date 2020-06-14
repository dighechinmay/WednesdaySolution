package com.chinmay.itunesappwednesday.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.chinmay.itunesappwednesday.model.database.AppDatabase
import com.chinmay.itunesappwednesday.ui.ItunesSearchListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItunesSearchListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "result").build()
            @Suppress("UNCHECKED_CAST")
            return ItunesSearchListViewModel(db.itunesDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}