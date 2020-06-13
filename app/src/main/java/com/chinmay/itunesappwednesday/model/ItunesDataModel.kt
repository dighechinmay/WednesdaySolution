package com.chinmay.itunesappwednesday.model

class ItunesDataModel {


    data class Response(

        val results: List<Results>
    )


    data class Results(
        val artistName: String,
        val collectionName: String,
        val trackName: String,
        val artistId: Int,
        val trackId: Int,
        val artworkUrl100: String
    )
}