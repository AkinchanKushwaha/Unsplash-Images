package com.example.unsplashimages.viewModel


import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unsplashimages.model.UnsplashData
import com.example.unsplashimages.model.UnsplashService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel: ViewModel() {

    private val unsplashService = UnsplashService()
    private val disposable = CompositeDisposable()

    val imagesData = MutableLiveData<UnsplashData>()
    val imageLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchImages()
    }

    private fun fetchImages(){
        loading.value = true
        disposable.add(
            unsplashService.getImages()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<UnsplashData>(){
                    override fun onSuccess(value: UnsplashData?) {
                        imagesData.value = value
                        loading.value = false
                        d("Images ", imagesData.value.toString())
                        imageLoadError.value = false
                    }

                    override fun onError(e: Throwable?) {
                        imageLoadError.value = false
                        loading.value = false
                    }

                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}