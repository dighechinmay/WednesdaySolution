package com.chinmay.itunesappwednesday.injection.component

import com.chinmay.itunesappwednesday.injection.module.NetworkModule
import com.chinmay.itunesappwednesday.ui.ItunesSearchListViewModel
import dagger.Component
import javax.inject.Singleton

interface ViewModellInjector {

}
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(itunesDetailListViewModel: ItunesSearchListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }

}