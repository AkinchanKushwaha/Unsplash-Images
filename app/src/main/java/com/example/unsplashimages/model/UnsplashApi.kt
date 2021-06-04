package com.example.unsplashimages.model

import io.reactivex.Single
import retrofit2.http.GET

interface UnsplashApi {
    @GET("list")
    fun getImages(): Single<UnsplashData>

}