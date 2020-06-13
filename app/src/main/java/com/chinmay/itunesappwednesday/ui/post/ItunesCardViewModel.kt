package com.chinmay.itunesappwednesday.ui.post

import androidx.lifecycle.MutableLiveData
import com.chinmay.itunesappwednesday.model.ItunesDataModel
import com.chinmay.itunesappwednesday.viewmodel.BaseViewModel

class ItunesCardViewModel:BaseViewModel() {
    private val trackTitle = MutableLiveData<String>()
    private val artist = MutableLiveData<String>()

    fun bind(result: ItunesDataModel.Results){
        trackTitle.value = result.trackName
        artist.value = result.artistName
    }

    fun getTrackTitle():MutableLiveData<String>{
        return trackTitle
    }

    fun getArtist():MutableLiveData<String>{
        return artist
    }
}