package com.chinmay.itunesappwednesday.ui.post

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chinmay.itunesappwednesday.R
import com.chinmay.itunesappwednesday.databinding.ActivitySearchResultsBinding
import com.chinmay.itunesappwednesday.ui.ItunesSearchListViewModel
import com.google.android.material.snackbar.Snackbar

class ItunesSearchActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySearchResultsBinding
    private lateinit var viewModel: ItunesSearchListViewModel

    private var errorSnackbar: Snackbar? = null


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_results)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        viewModel = ViewModelProviders.of(this).get(ItunesSearchListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
    }


    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }
}