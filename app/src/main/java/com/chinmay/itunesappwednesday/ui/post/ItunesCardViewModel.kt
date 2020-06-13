package com.chinmay.itunesappwednesday.ui.post

import androidx.lifecycle.MutableLiveData
import com.chinmay.itunesappwednesday.model.ItunesDataModel
import com.chinmay.itunesappwednesday.viewmodel.BaseViewModel

class ItunesCardViewModel:BaseViewModel() {
    private val trackTitle = MutableLiveData<String>()
    private val artist = MutableLiveData<String>()
    private val collection = MutableLiveData<String>()
    private val url = MutableLiveData<String>()


    fun bind(result: ItunesDataModel.Results){
        trackTitle.value = result.trackName
        artist.value = result.artistName
        collection.value = result.collectionName
        url.value = result.artworkUrl100
    }

    fun getTrackTitle():MutableLiveData<String>{
        return trackTitle
    }

    fun getArtist():MutableLiveData<String>{
        return artist
    }


    fun getCollection():MutableLiveData<String>{
        return collection
    }

    fun getUrl():MutableLiveData<String>{
        return url
    }
}