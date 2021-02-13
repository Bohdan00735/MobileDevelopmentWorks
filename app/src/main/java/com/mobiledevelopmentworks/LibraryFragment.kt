package com.mobiledevelopmentworks

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TextView
import com.mobiledevelopmentworks.ui.adittion.AddBookActivity
import com.mobiledevelopmentworks.ui.adittion.BookInfoActivity

/**
 * A simple [Fragment] subclass.
 * Use the [LibraryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("CAST_NEVER_SUCCEEDS")
class LibraryFragment : Fragment() {

    private lateinit var root: View
    lateinit var books: List<Book>
    private var isPair = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_library, container, false)
        books = Utils().parseJSON(requireActivity())
        root.findViewById<Button>(R.id.load_books_button).setOnClickListener{
            val intent = Intent(activity, AddBookActivity::class.java)
            startActivity(intent)
        }

        addScannedBooks()
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
        columnView = tableRowModel.findViewById(R.id.price_column)
        columnView.text = book.price
        val imageView = tableRowModel.findViewById<ImageView>(R.id.image_column)
        imageView.setImageBitmap(Utils().getBitmapOfImage(book.imagePath, requireActivity()))
        if (book.isbn13 as? Int is Int) tableLayout.id = book.isbn13.toInt()
        if (isPair) tableRowModel.setBackgroundColor(requireContext().resources.getColor(R.color.basic_green))
        isPair = !isPair
        tableRowModel.setOnClickListener(){
            openBookInformation(book.isbn13)
        }
        tableLayout.addView(tableRowModel)
    }

    private fun openBookInformation(isbn13: String) {
        val intent = Intent(activity, BookInfoActivity::class.java)
        intent.putExtra("bookCode", isbn13)
        startActivity(intent)
    }
}