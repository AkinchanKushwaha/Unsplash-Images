package com.example.unsplashimages.model

import com.example.unsplashimages.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class UnsplashService {

    @Inject
    lateinit var api: UnsplashApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getImages(): Single<UnsplashData> {
        return api.getImages()
    }

}