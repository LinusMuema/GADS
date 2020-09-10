package com.moose.gads.features.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.moose.gads.R
import com.moose.gads.features.submit.SubmitActivity
import com.moose.gads.utils.hideBottomBar
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.home_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel:HomeViewModel by viewModel()
    private val titles = arrayOf("Learning Leaders", "Skill IQ Leaders")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        this.hideBottomBar()

        //Setup ActionBar
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.home_toolbar)


        //Setup viewpager
        val adapter = HomeTabAdapter(this)
        pager.adapter = adapter
        TabLayoutMediator(tab, pager){ tab, position ->
            tab.text = titles[position]
        }.attach()

        //Get data from GADS api
        viewModel.getData()

        //Move to submit activity
        submit.setOnClickListener {
            startActivity(Intent(this, SubmitActivity::class.java))
        }
    }
}