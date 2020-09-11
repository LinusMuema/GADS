package com.moose.gads.features.submit

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.widget.LinearLayout.LayoutParams
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.moose.gads.R
import com.moose.gads.features.home.HomeActivity
import com.moose.gads.utils.CustomDialog
import com.moose.gads.utils.hideBottomBar
import kotlinx.android.synthetic.main.activity_submit.*
import kotlinx.android.synthetic.main.submit_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SubmitActivity : AppCompatActivity() {
    private val fieldError = "Field cannot be empty"
    private val viewModel: SubmitViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        this.hideBottomBar()

        //Setup ActionBar
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(R.layout.submit_toolbar)

        viewModel.exception.observe(this, {
            if (it == "success") {
                showDialog("response", true)
                firstname_input.text.clear()
                lastname_input.text.clear()
                email_input.text.clear()
                repo_input.text.clear()
            }
            else showDialog("response", false)
        })

        submit.setOnClickListener {
            when {
                firstname_input.text.isEmpty() -> firstname_input.error = fieldError
                lastname_input.text.isEmpty() -> lastname_input.error = fieldError
                email_input.text.isEmpty() -> email_input.error = fieldError
                repo_input.text.isEmpty() -> repo_input.error = fieldError
                else -> {
                    showDialog("confirm", false)
                }
            }
        }

        //Back to Homepage
        btn_back.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun showDialog(type: String, response: Boolean){
        val dialog = CustomDialog(this, type, response)
        if (type == "confirm"){
            dialog.setOnDismissListener {
                viewModel.submitProject(
                    firstname_input.text.toString(),
                    lastname_input.text.toString(),
                    email_input.text.toString(),
                    repo_input.text.toString()
                )
            }
        }
        dialog.show()
        dialog.window!!.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        dialog.window!!.setBackgroundDrawableResource(R.drawable.dialog_background)
    }

}