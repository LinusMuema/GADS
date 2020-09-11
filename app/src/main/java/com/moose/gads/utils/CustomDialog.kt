package com.moose.gads.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.moose.gads.R
import kotlinx.android.synthetic.main.dialog_confirm.*
import kotlinx.android.synthetic.main.dialog_response.*
import retrofit2.Response

class CustomDialog(context: Context, private val type: String, private val response: Boolean) : Dialog(context){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.setCanceledOnTouchOutside(true)
        when (type){
            "confirm" -> {
                setContentView(R.layout.dialog_confirm)
                cancel_action.setOnClickListener { cancel() }
                confirm_button.setOnClickListener { dismiss() }
            }
            "response" -> {
                setContentView(R.layout.dialog_response)
                if (response) {
                    dialog_image.loadDrawable(R.drawable.success)
                    dialog_message.text = "Submission Successful"
                }
                else {
                    dialog_image.loadDrawable(R.drawable.error)
                    dialog_message.text = "Submission not Successful"
                }
            }
        }
    }
}