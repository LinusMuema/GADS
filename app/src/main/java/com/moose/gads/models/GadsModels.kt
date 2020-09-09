package com.moose.gads.models

data class GadsModels(val topLearners: List<LearningLeaders>, val topSkills: List<SkillsLeaders>)

data class LearningLeaders(var name: String, var hours: Double, var country: String, var badgeUrl: String)

data class SkillsLeaders(var name : String, var score : Long, var country: String, var badgeUrl : String)