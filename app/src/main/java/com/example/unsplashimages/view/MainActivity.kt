package com.example.unsplashimages.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unsplashimages.R
import com.example.unsplashimages.viewModel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ListViewModel
    private val imagesListAdapter = ImagesListAdapter(this@MainActivity, arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recyclerViewImages.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = imagesListAdapter
        }

        swipeRefreshLayoutMain.setOnRefreshListener {
            swipeRefreshLayoutMain.isRefreshing = false
            viewModel.refresh()
        }
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.imagesData.observe(this, { unsplashData ->
            unsplashData?.let {
                imagesListAdapter.updateImages(it)
            }
        })

        viewModel.imageLoadError.observe(this, { isError ->
            isError?.let {
                tv_error.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    recyclerViewImages.visibility = View.GONE
                }
            }
        })

        viewModel.loading.observe(this, { isLoading ->
            isLoading?.let {
                progress_bar_loading.visibility = if (it) View.VISIBLE else View.GONE
                recyclerViewImages.visibility = if (it) View.GONE else View.VISIBLE
            }
        })
    }
}