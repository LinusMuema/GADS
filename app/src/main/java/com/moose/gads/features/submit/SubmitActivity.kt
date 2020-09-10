package com.moose.gads.features.submit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.moose.gads.R
import com.moose.gads.features.home.HomeActivity
import com.moose.gads.utils.hideBottomBar
import kotlinx.android.synthetic.main.submit_toolbar.*

class SubmitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        this.hideBottomBar()

        //Setup ActionBar
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.submit_toolbar)

        //Back to Homepage
        btn_back.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}