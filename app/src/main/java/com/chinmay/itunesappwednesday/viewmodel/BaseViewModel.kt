package com.chinmay.itunesappwednesday.viewmodel

import androidx.lifecycle.ViewModel
import com.chinmay.itunesappwednesday.injection.component.DaggerViewModelInjector
import com.chinmay.itunesappwednesday.injection.component.ViewModelInjector
import com.chinmay.itunesappwednesday.injection.module.NetworkModule
import com.chinmay.itunesappwednesday.ui.ItunesSearchListViewModel

abstract class BaseViewModel:ViewModel() {

private val injector: ViewModelInjector = DaggerViewModelInjector
    .builder()
    .networkModule(NetworkModule)
    .build()

init {
    inject()
}

/**
 * Injects the required dependencies
 */
private fun inject() {
    when (this) {
        is ItunesSearchListViewModel -> injector.inject(this)
    }
}

}