package com.moose.gads.features

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {
    val composite = CompositeDisposable()
    val exception: MutableLiveData<String> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        composite.clear()
    }
}