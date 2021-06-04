package com.example.unsplashimages.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unsplashimages.R
import com.example.unsplashimages.model.UnsplashDataItem
import kotlinx.android.synthetic.main.item_image.view.*

class ImagesListAdapter(
    private val context: Context,
    private val list: ArrayList<UnsplashDataItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateImages(newImages: ArrayList<UnsplashDataItem>) {
        list.clear()
        list.addAll(newImages)
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MyViewHolder) {
            holder.itemView.textViewAuthor.text = list[position].author
            Glide
                .with(context)
                .load(list[position].download_url)
                .into(holder.itemView.iv_image)
        }
    }

    override fun getItemCount() = list.size

}