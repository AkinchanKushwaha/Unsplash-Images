package com.example.unsplashimages.model

import com.example.unsplashimages.di.DaggerApiComponent
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import javax.inject.Inject

@Module
class UnsplashService {
    @Inject
    lateinit var api: UnsplashApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    @Provides
    fun getImages(): Single<UnsplashData> {
        return api.getImages()
    }

}