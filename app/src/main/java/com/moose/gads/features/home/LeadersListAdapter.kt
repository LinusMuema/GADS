package com.moose.gads.features.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moose.gads.R
import com.moose.gads.models.GadsModels
import com.moose.gads.models.LearningLeaders
import com.moose.gads.models.SkillsLeaders
import com.moose.gads.utils.loadImage
import com.moose.gads.utils.setDimens
import kotlinx.android.synthetic.main.leaders_list_item.view.*

class LeadersListAdapter(private val data: GadsModels, private val type: String): RecyclerView.Adapter<LeadersListAdapter.LeadersViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeadersViewHolder {
        return LeadersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.leaders_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: LeadersViewHolder, position: Int) {
        when(type){
            "skills" -> holder.bindSkills(data.topSkills[position])
            else -> holder.bindHours(data.topLearners[position])
        }
    }

    override fun getItemCount(): Int {
        return when(type){
            "skills" -> data.topSkills.size
            else -> data.topLearners.size
        }
    }

    inner class LeadersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val context = itemView.context
        fun bindSkills(skillsLeaders: SkillsLeaders) {
            itemView.name.text = skillsLeaders.name
            itemView.details.text = context.resources.getString(R.string.details, skillsLeaders.score.toInt(), "Skill IQ", skillsLeaders.country)
            itemView.badge.setDimens(150, 80)
            itemView.badge.loadImage(skillsLeaders.badgeUrl)
        }

        fun bindHours(learningLeaders: LearningLeaders) {
            itemView.name.text = learningLeaders.name
            itemView.details.text = context.resources.getString(R.string.details, learningLeaders.hours.toInt(), "learning hours", learningLeaders.country)
            itemView.badge.setDimens(100, 100)
            itemView.badge.loadImage(learningLeaders.badgeUrl)
        }
    }

}