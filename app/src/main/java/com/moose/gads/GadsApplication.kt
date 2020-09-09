package com.moose.gads

import android.app.Application
import com.moose.gads.di.networkModule
import com.moose.gads.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class GadsApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        //Initialise dependency injection
        startKoin {
            androidContext(this@GadsApplication)
            modules(
                networkModule,
                viewModelModule
            )
        }
    }
}