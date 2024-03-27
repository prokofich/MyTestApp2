package com.ddd.mytestapp2.model.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddd.mytestapp2.R

class ImageAdapter: RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_for_rv,parent,false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {}


}