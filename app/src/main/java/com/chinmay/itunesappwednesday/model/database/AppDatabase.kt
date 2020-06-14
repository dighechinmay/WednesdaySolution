package com.chinmay.itunesappwednesday.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chinmay.itunesappwednesday.model.ItunesDAO
import com.chinmay.itunesappwednesday.model.ItunesDataModel

@Database(entities = [ItunesDataModel.Results::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itunesDao(): ItunesDAO
}