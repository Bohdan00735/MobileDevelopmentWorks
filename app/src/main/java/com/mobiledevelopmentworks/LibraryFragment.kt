package com.mobiledevelopmentworks

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TextView
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 * Use the [LibraryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LibraryFragment : Fragment() {

    private lateinit var root: View
    lateinit var books: List<Book>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_library, container, false)
        books = Utils().parseJSON(requireActivity())
        root.findViewById<Button>(R.id.load_books_button).setOnClickListener{
            addScannedBooks()
        }
        return root
    }

    private fun addScannedBooks(){
        val tableLayout = root.findViewById<TableLayout>(R.id.books_table)
        for (i in books){
            addRow(tableLayout, i)
        }
        tableLayout.setColumnShrinkable(0, tableLayout.isColumnShrinkable(0))
    }

    private fun addRow(tableLayout: TableLayout, book: Book) {
        val tableRowModel = LayoutInflater.from(activity).inflate(R.layout.table_row_book, null)
        var columnView = tableRowModel.findViewById<TextView>(R.id.title_column)
        columnView.text = book.title
        columnView = tableRowModel.findViewById(R.id.subtitle_column)
        columnView.text = book.subtitle
        columnView = tableRowModel.findViewById(R.id.isbn13_column)
        columnView.text = book.isbn13
        columnView = tableRowModel.findViewById(R.id.price_column)
        columnView.text = book.price
        val imageView = tableRowModel.findViewById<ImageView>(R.id.image_column)
        imageView.setImageBitmap(Utils().getBitmapOfImage(book.imagePath, requireActivity()))
        tableLayout.addView(tableRowModel)
    }
}