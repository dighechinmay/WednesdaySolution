package com.chinmay.itunesappwednesday.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.chinmay.itunesappwednesday.R
import com.chinmay.itunesappwednesday.model.ItunesDAO
import com.chinmay.itunesappwednesday.model.ItunesDataModel
import com.chinmay.itunesappwednesday.network.ItunesApi
import com.chinmay.itunesappwednesday.viewmodel.BaseViewModel
import com.chinmay.wednesdayitunesapp.ui.post.ItunesListAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ItunesSearchListViewModel(private val itunesDAO: ItunesDAO):BaseViewModel() {

    var isConn:Boolean = false

    @Inject
    lateinit var itunesApi: ItunesApi
    var itunesListAdapter:ItunesListAdapter =  ItunesListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { searchDetails(queryString) }

    private lateinit var subscription: Disposable


    val editText = MutableLiveData<String>()
    var queryString: String = ""



    fun onClickSearch(){

            searchDetails(queryString)


    }

    var queryTextWatcher: TextWatcher = object: TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            queryString = p0.toString()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

    }


    private fun searchDetails(inputQuery: String) {
        if(isConn) {
            subscription =
                    itunesApi.getSearchResults(inputQuery).concatMap { apiItunesList ->
                    itunesDAO.insertAll(*apiItunesList.results.toTypedArray())
                    Observable.just(apiItunesList.results) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveListStart() }
                .doOnTerminate { onRetrieveListFinish() }
                .subscribe(
                    { result ->   onRetrieveSuccess(result)},
                    { onRetrieveError() }
                )
        }
        else{
            var conditionDB = "%$inputQuery%"
            subscription =
                Observable.fromCallable { itunesDAO.getParticular(conditionDB) }
                    .concatMap { dbItunesList ->
                        Observable.just(dbItunesList)
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { onRetrieveListStart() }
                    .doOnTerminate { onRetrieveListFinish() }
                    .subscribe(
                        { result -> onRetrieveSuccess(result) },
                        { onRetrieveError() }
                    )
        }
    }

    private fun onRetrieveListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }


    private fun onRetrieveError() {
        errorMessage.value = R.string.post_error
    }

    private fun onRetrieveSuccess(response: List<ItunesDataModel.Results>) {
        itunesListAdapter.updatePostList(response)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }




}