package com.mobiledevelopmentworks.ui.adittion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.TextUtils.isEmpty
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import com.mobiledevelopmentworks.Book
import com.mobiledevelopmentworks.R
import com.mobiledevelopmentworks.Utils
import java.lang.Exception

class AddBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        supportActionBar!!.title = Html.fromHtml("<font color='#4D4637'>New Book </font>")

        val setTitle = findViewById<EditText>(R.id.title_book_input)
        val setSubtitle = findViewById<EditText>(R.id.subtitle_book_input)
        val setPrice = findViewById<EditText>(R.id.price_book_input)

        findViewById<Button>(R.id.add_new_book_button).setOnClickListener{
            addBook(setTitle.text.toString(), setSubtitle.text.toString(), setPrice.text.toString())
        }

        setTitle.doAfterTextChanged {
            if (setTitle.text.toString().isEmpty()) setTitle.error = "input smth"
        }

        setSubtitle.doAfterTextChanged {
            if (setTitle.text.toString().isEmpty()) setTitle.error = "input smth"
        }
    }

    private fun addBook(title: String, subtitle: String, price:String) {
        if (checkPrice(price)) return
        Utils().addToList(Book(title, subtitle,"", price, ""), this)
    }

    private fun checkPrice(price: String): Boolean {
        try {
            price.toFloat()
        }catch (e:Exception){
            findViewById<EditText>(R.id.price_book_input).error = "only positive values available"
            return false
        }
        return true
    }


}