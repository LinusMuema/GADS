package com.moose.gads.features.home

import androidx.lifecycle.MutableLiveData
import com.moose.gads.features.BaseViewModel
import com.moose.gads.models.GadsModels
import com.moose.gads.models.LearningLeaders
import com.moose.gads.models.SkillsLeaders
import com.moose.gads.network.GadsEndpoints
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val service: GadsEndpoints): BaseViewModel(){
    val data: MutableLiveData<GadsModels> = MutableLiveData()

    fun getData() {
        val hours = service.getTopHours()
        val skills = service.getTopSkills()
        val results = Observable.zip(hours, skills,
            BiFunction<List<LearningLeaders>, List<SkillsLeaders>, GadsModels> { learners, skills ->
            // here we get both the results at a time.
            return@BiFunction GadsModels(learners, skills)
        })
        composite.add(
            results.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {data.value = it},
                    {exception.value = it.message}))
    }

}