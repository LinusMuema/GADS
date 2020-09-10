package com.moose.gads.features.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.moose.gads.R
import com.moose.gads.features.home.HomeViewModel
import com.moose.gads.features.home.LeadersListAdapter
import com.moose.gads.utils.hide
import com.moose.gads.utils.show
import kotlinx.android.synthetic.main.fragment_learning.*
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LearningFragment : Fragment() {

    private val viewModel:HomeViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_learning, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(viewLifecycleOwner, Observer {
            learners_loading.hide()
            learners_recycler.show()
            learners_recycler.apply {
                setHasFixedSize(true)
                adapter = LeadersListAdapter(it, "learners")
            }
        })
    }
}