package com.mobiledevelopmentworks.ui.adittion

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.mobiledevelopmentworks.Book
import com.mobiledevelopmentworks.R
import com.mobiledevelopmentworks.Utils
import java.lang.Error
import java.lang.Exception
import java.lang.NullPointerException

@Suppress("CAST_NEVER_SUCCEEDS")
class BookInfoActivity : AppCompatActivity() {
    private lateinit var book: Book
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_info)
        supportActionBar!!.title = Html.fromHtml("<font color='#4D4637'>Book Info </font>")

        val isbn13 = intent.extras!!["bookCode"].toString()

        try {
            book = Utils().getBookInfoFromJSON(this, "$isbn13.txt")
        }catch (ex:NullPointerException){
            book = Utils().getBookFromList(this, isbn13)!!
        }
        fillPage()
    }

    private fun fillPage() {
        setBookTitle(book.title)
        setBookImage(Utils().getBitmapOfImage(book.imagePath, this))
        setAuthors(book.authors)
        setSubtitle(book.subtitle)
        setRating(book.rating)
        setDescription(book.description)
        setPublisher(book.publisher)
        setYear(book.year)
        setPages(book.pages)
        setPrice(book.price)
    }

    private fun setPrice(price: String) {
        findViewById<TextView>(R.id.book_price_info).text = price
    }

    private fun setPages(pages: String) {
        findViewById<TextView>(R.id.book_num_pages_info).text = pages
    }

    private fun setYear(year: String) {
        findViewById<TextView>(R.id.book_year_info).text = year
    }

    private fun setPublisher(publisher: String) {
        findViewById<TextView>(R.id.book_publisher_info).text = publisher
    }

    private fun setDescription(description: String) {
        findViewById<TextView>(R.id.book_description_info).text = description
    }

    private fun setSubtitle(subtitle: String) {
        findViewById<TextView>(R.id.book_subtitle_info).text = subtitle
    }

    private fun setRating(rating: String) {
        try {
            findViewById<RatingBar>(R.id.book_ratingbar).rating = rating.toFloat()
        }catch (e:Exception){}

        findViewById<TextView>(R.id.book_rating_value).text = rating
    }

    private fun setAuthors(authors: String) {
        findViewById<TextView>(R.id.book_authors).text = authors.replace(",", "\n")
    }

    private fun setBookImage(bitmapOfImage: Bitmap?) {
        if (bitmapOfImage != null) findViewById<ImageView>(R.id.bookcover_view).setImageBitmap(bitmapOfImage)
    }

    private fun setBookTitle(title: String) {
        findViewById<TextView>(R.id.book_title_view).text = title
    }
}