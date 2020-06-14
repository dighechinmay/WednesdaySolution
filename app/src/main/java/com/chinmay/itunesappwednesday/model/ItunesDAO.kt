package com.chinmay.itunesappwednesday.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItunesDAO {

    @get:Query("SELECT * FROM results")
    val all: List<ItunesDataModel.Results>

    @Insert
    fun insertAll(vararg results: ItunesDataModel.Results)

    @Query("select * from results where artistName like :arg0")
    fun getParticular(arg0: String): List<ItunesDataModel.Results>


}