package com.moose.gads.di

import com.moose.gads.features.home.HomeViewModel
import com.moose.gads.features.submit.SubmitViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(service = get()) }
    viewModel { SubmitViewModel(service = get()) }
}