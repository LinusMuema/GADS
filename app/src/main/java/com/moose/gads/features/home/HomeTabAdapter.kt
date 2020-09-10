package com.moose.gads.features.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.moose.gads.features.home.fragments.LearningFragment
import com.moose.gads.features.home.fragments.SkillsFragment

class HomeTabAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            1 -> SkillsFragment()
            else -> LearningFragment()
        }
    }

}