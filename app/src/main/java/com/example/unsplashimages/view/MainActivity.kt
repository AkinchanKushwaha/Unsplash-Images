package com.example.unsplashimages.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.unsplashimages.R
import com.example.unsplashimages.viewModel.ListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        viewModel.refresh()
    }
}