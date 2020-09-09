package com.moose.gads.features.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.moose.gads.R
import com.moose.gads.utils.hideBottomBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        this.hideBottomBar()
        viewModel.getData()

        viewModel.data.observe(this, Observer {
            Log.d("Data", "onCreate: $it")
        })
    }
}