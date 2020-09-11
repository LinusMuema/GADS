package com.moose.gads.features.submit

import com.moose.gads.features.BaseViewModel
import com.moose.gads.network.GoogleFormEndpoints
import io.reactivex.android.schedulers.AndroidSchedulers

class SubmitViewModel(private val service: GoogleFormEndpoints) : BaseViewModel(){

    fun submitProject(firstName: String, lastName: String, email: String, repo: String) {
        composite.add(
            service.submitProject(email, firstName, lastName, repo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { exception.value = "success" },
                    { exception.value = it.message })
        )
    }

}