package com.moose.gads.network

import com.moose.gads.models.LearningLeaders
import com.moose.gads.models.SkillsLeaders
import io.reactivex.Observable
import retrofit2.http.GET

interface GadsEndpoints {
    @GET("/api/hours")
    fun getTopHours(): Observable<List<LearningLeaders>>

    @GET("/api/skilliq")
    fun getTopSkills(): Observable<List<SkillsLeaders>>
}