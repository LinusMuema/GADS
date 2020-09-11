package com.moose.gads.features.submit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import com.moose.gads.R
import com.moose.gads.features.home.HomeActivity
import com.moose.gads.utils.CustomDialog
import com.moose.gads.utils.hideBottomBar
import kotlinx.android.synthetic.main.activity_submit.*
import kotlinx.android.synthetic.main.leaders_list_item.view.*
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
            if (it == "success") CustomDialog(this, "response", true).show()
            else CustomDialog(this, "response", false).show()
        })

        submit.setOnClickListener {
            when {
                firstname_input.text.isEmpty() -> firstname_input.error = fieldError
                lastname_input.text.isEmpty() -> lastname_input.error = fieldError
                email_input.text.isEmpty() -> email_input.error = fieldError
                repo_input.text.isEmpty() -> repo_input.error = fieldError
                else -> {
                    val dialog = CustomDialog(this, "confirm", true)
                    dialog.setOnDismissListener {
                        viewModel.submitProject(
                            firstname_input.text.toString(),
                            lastname_input.text.toString(),
                            email_input.text.toString(),
                            repo_input.text.toString()
                        )
                    }
                    dialog.show()
                }
            }
        }

        //Back to Homepage
        btn_back.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

}