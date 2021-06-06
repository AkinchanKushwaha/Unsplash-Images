package com.example.unsplashimages.di

import com.example.unsplashimages.model.UnsplashService
import com.example.unsplashimages.viewModel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: UnsplashService)
    fun inject(viewModel: ListViewModel)
}