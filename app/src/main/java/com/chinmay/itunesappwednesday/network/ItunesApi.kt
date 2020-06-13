package com.chinmay.itunesappwednesday.network

import com.chinmay.itunesappwednesday.model.ItunesDataModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesApi {

    @GET("/search")
    fun getSearchResults(@Query("term") term: String): Observable<ItunesDataModel.Response>
}