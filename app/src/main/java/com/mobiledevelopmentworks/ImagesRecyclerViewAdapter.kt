package com.mobiledevelopmentworks


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.alibaba.android.vlayout.VirtualLayoutManager;
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class ImagesRecyclerViewAdapter(private val context: Context?, private val data: MutableList<String>) :
    RecyclerView.Adapter<ImagesRecyclerViewAdapter.ImagesViewHolder>(){

    class ImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var imageView: ImageView? = null

        init {
            imageView = itemView.findViewById(com.mobiledevelopmentworks.R.id.gallery_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.image_recyclerview_element, parent, false)
        return ImagesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val layoutParams  = VirtualLayoutManager.LayoutParams(100, 100)
        Picasso.with(context).load(data[position])
            .error(R.drawable.ic_baseline_image_not_supported)
            .placeholder(R.drawable.ic_baseline_image)
            .into(holder.imageView)

        if ((position-1)%8 == 0) {
            layoutParams.height = 300;
            layoutParams.width = 300;
        }



    }


    override fun getItemCount(): Int {
        return data.size
    }

    fun getItem(id: Int): String {
        return data[id]
    }

}
