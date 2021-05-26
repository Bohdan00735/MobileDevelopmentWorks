package com.mobiledevelopmentworks

import android.R.attr.data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView


@Suppress("DEPRECATION")
class BooksRecyclerViewAdapter(private var books: ArrayList<Book>, private val context: FragmentActivity,
                               val clickListener: (String) -> Unit) :
    RecyclerView.Adapter<BooksRecyclerViewAdapter.BooksViewHolder>(){


    class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title:TextView? = null
        var subtitle:TextView? = null
        var price:TextView? = null
        var bookImage:ImageView? = null

        lateinit var isbn:String

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
        val book = books[position]
        holder.title?.text = books[position].title
        holder.subtitle?.text = books[position].subtitle
        holder.price?.text = books[position].price
        val imageBitmapF = Utils().getBitmapOfImage(books[position].imagePath, context)
        if (imageBitmapF != null)
            holder.bookImage?.setImageBitmap(imageBitmapF)
        else
            holder.bookImage?.setImageResource(R.drawable.ic_book)

        holder.isbn = books[position].isbn13

        holder?.itemView?.setOnClickListener { clickListener(book.isbn13) }
        /*Picasso.with(context).load(Utils().getImageFromAssets("Image_01.png", context))
            .error(R.drawable.ic_book)
            .placeholder(R.drawable.ic_book)
            .into(holder.bookImage)*/

    }

    fun removeItem(position: Int) {
        books.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: Book, position: Int) {
        books.add(position, item)
        notifyItemInserted(position)
    }

    fun getData(): ArrayList<Book> {
        return books
    }

    fun updateList(newBooksList: ArrayList<Book>){
        books = newBooksList
        notifyDataSetChanged()
    }

    override fun getItemCount() = books.size
}