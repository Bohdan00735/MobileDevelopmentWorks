package com.mobiledevelopmentworks

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.io.File
import java.net.URI
import java.net.URL

class BooksRecyclerViewAdapter(private val books: List<Book>, private val context: FragmentActivity) :
    RecyclerView.Adapter<BooksRecyclerViewAdapter.BooksViewHolder>(){
    class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title:TextView? = null
        var subtitle:TextView? = null
        var price:TextView? = null
        var bookImage:ImageView? = null

        init {
            title = itemView.findViewById(R.id.title_element)
            subtitle = itemView.findViewById(R.id.subtitle_element)
            price = itemView.findViewById(R.id.price_element)
            bookImage = itemView.findViewById(R.id.image_element)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycleview_element, parent, false)
        return BooksViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.title?.text = books[position].title
        holder.subtitle?.text = books[position].subtitle
        holder.price?.text = books[position].price
        holder.bookImage?.setImageBitmap(Utils().getBitmapOfImage(books[position].imagePath, context))
        context.assets.list("/images/")?.forEach {
            print(it.toString())
        }
        /*Picasso.with(context).load(Utils().getImageFromAssets("Image_01.png", context))
            .error(R.drawable.ic_book)
            .placeholder(R.drawable.ic_book)
            .into(holder.bookImage)*/

    }

    override fun getItemCount() = books.size
}