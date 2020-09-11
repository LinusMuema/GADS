package com.moose.gads.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.moose.gads.R

fun View.fadeIn() = this.startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.fade_in))

fun View.show() { this.visibility = View.VISIBLE }

fun View.hide() { this.visibility = View.GONE }

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 5f
        centerRadius = 25f
        start()
    }
}

fun ImageView.loadImage(uri: String){
    val options = RequestOptions()
        .placeholder(getProgressDrawable(this.context))
        .error(R.drawable.image_error)

    Glide.with(this.context).setDefaultRequestOptions(options).load(uri).into(this)
}

fun ImageView.loadDrawable(id: Int){
    val options = RequestOptions()
        .placeholder(getProgressDrawable(this.context))
        .error(R.drawable.image_error)

    Glide.with(this.context).setDefaultRequestOptions(options).load(id).into(this)
}

fun View.setDimens(width: Int, height:Int){
    this.requestLayout()
    this.layoutParams.width = width
    this.layoutParams.height = height
}


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