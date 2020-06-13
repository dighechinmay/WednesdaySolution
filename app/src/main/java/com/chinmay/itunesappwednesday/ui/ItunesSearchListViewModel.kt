package com.chinmay.itunesappwednesday.ui

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.chinmay.itunesappwednesday.R
import com.chinmay.itunesappwednesday.model.ItunesDataModel
import com.chinmay.itunesappwednesday.network.ItunesApi
import com.chinmay.itunesappwednesday.viewmodel.BaseViewModel
import com.chinmay.wednesdayitunesapp.ui.post.ItunesListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ItunesSearchListViewModel:BaseViewModel() {
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
            //  Log.i("during",p0.toString())
        }

    }

    private fun searchDetails(inputQuery: String) {
        subscription = itunesApi.getSearchResults(inputQuery)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveListStart() }
            .doOnTerminate { onRetrieveListFinish() }
            .subscribe(

                { response -> onRetrieveSuccess(response) },
                { onRetrieveError() }
            )
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

    private fun onRetrieveSuccess(response: ItunesDataModel.Response) {
        itunesListAdapter.updatePostList(response.results)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }




}