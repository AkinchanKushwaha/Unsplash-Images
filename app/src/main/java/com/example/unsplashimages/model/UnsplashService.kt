package com.example.unsplashimages.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UnsplashService {
    private val BASE_URL = "https://picsum.photos/v2/"

    private val api: UnsplashApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(UnsplashApi::class.java)

    fun getImages(): Single<UnsplashData> {
        return api.getImages()
    }

}