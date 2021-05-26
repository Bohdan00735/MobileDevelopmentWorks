package com.mobiledevelopmentworks

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mobiledevelopmentworks.ui.adittion.AddBookActivity
import com.mobiledevelopmentworks.ui.adittion.BookInfoActivity


/**
 * A simple [Fragment] subclass.
 * Use the [LibraryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("CAST_NEVER_SUCCEEDS", "DEPRECATION")
class LibraryFragment : Fragment() {

    private lateinit var root: View
    lateinit var fullBooksList: MutableList<Book>
    lateinit var recycleBookList: MutableList<Book>
    private var ADD_BOOK_IND = 0;
    lateinit var adapter: BooksRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_library, container, false)
        fullBooksList = Utils().parseJSON(requireActivity()).toMutableList()
        recycleBookList = fullBooksList
        root.findViewById<Button>(R.id.load_books_button).setOnClickListener{
            val intent = Intent(activity, AddBookActivity::class.java)
            startActivityForResult(intent, ADD_BOOK_IND)
        }


        recyclerView = root.findViewById(R.id.books_recyclerview)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = BooksRecyclerViewAdapter(recycleBookList as ArrayList<Book>, requireActivity()){ isbn13:String ->
            openBookInformation(isbn13)
        }
        recyclerView.adapter = adapter

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val swipeAdapter = recyclerView.adapter as BooksRecyclerViewAdapter
                swipeAdapter.removeItem(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        val searchLine = root.findViewById<SearchView>(R.id.book_search)
        searchLine.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchLine.clearFocus()
                return findInBooks(query)
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return root
    }

    private fun findInBooks(query: String?): Boolean {

        val searchResultLeast = filterBooksList(query)
        if (searchResultLeast.size > 0){
            recycleBookList.clear()
            recycleBookList.addAll(searchResultLeast)
            recyclerView.adapter?.notifyDataSetChanged()
        }else{
            Toast.makeText(requireContext(), "Nothing found", Toast.LENGTH_LONG).show()
        }
        return false
    }

    private fun filterBooksList(query: String?) : MutableList<Book>{
        val newBooksList = mutableListOf<Book>()
        for (book in fullBooksList){
            if (query?.let { book.contain(it) }!!)
                newBooksList.add(book)
        }
        return newBooksList
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_BOOK_IND){
            if (resultCode == RESULT_OK){
                data?.getStringExtra("PRICE")?.let {
                    Book(
                        data.getStringExtra("TITLE")!!,
                        data.getStringExtra("SUBTITLE")!!,"",
                        it, "")
                }?.let { fullBooksList.add(it) }
                recycleBookList = fullBooksList
                recyclerView.adapter?.notifyItemInserted(recycleBookList.size-1)
            }
        }

    }

    private fun openBookInformation(isbn13: String) {
        val intent = Intent(activity, BookInfoActivity::class.java)
        intent.putExtra("bookCode", isbn13)
        startActivity(intent)
    }
}