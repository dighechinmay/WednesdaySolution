package com.chinmay.itunesappwednesday.model

import androidx.room.Entity
import androidx.room.PrimaryKey

class ItunesDataModel {


    data class Response(

        val results: List<Results>
    )


    @Entity
    data class Results(
        val artistName: String,
        val collectionName: String,
        val trackName: String,
        val artistId: Int,
        @field:PrimaryKey
        val trackId: Int,
        val artworkUrl100: String
    )
}