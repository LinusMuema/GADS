package com.moose.gads.utils

import android.app.Activity
import android.view.View
import android.view.animation.AnimationUtils
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.moose.gads.R

fun View.fadeIn() = this.startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.fade_in))

fun View.show() { this.visibility = View.VISIBLE }

fun View.hide() { this.visibility = View.GONE }

fun SwipeRefreshLayout.stopRefreshing() { this.isRefreshing = false }

fun Activity.hideBottomBar(){
    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
}


fun Activity.hideAllBars(){
    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
}