package com.mobiledevelopmentworks

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Utils {
    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun parseJSON(root: Context): List<Book> {
        val booksList = ArrayList<Book>()
        try {
            val jsonObject = JSONObject(getJsonDataFromAsset(root, "BooksList.txt"))
            val booksArray = jsonObject.getJSONArray("books")
            for (i in 0 until  booksArray.length()){
                val book = booksArray.getJSONObject(i)
                booksList.add(
                    Book(
                        book.getString("title"),
                        book.getString("subtitle"),
                        book.getString("isbn13"),
                        book.getString("price"),
                        book.getString("image")
                    )
                )
            }
        }catch (e: JSONException){
            e.printStackTrace()
        }
        return booksList
    }

    fun getBitmapOfImage(name: String, context: Context): Bitmap? {
        return getBitmapFromAssets(name, context)
    }

    private fun getBitmapFromAssets(fileName: String, context: Context): Bitmap? {
        return try {
            BitmapFactory.decodeStream(context.assets.open(fileName))
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun getBookFromList(context: Context, fileName: String): Book? {
        try {
            val jsonObject = JSONObject(getJsonDataFromAsset(context, "BooksList.txt"))
            val booksArray = jsonObject.getJSONArray("books")
            for (i in 0 until  booksArray.length()){
                val book = booksArray.getJSONObject(i)
                if (book.getString("isbn13").equals(fileName)){
                    return Book(
                        book.getString("title"),
                        book.getString("subtitle"),
                        book.getString("isbn13"),
                        book.getString("price"),
                        book.getString("image")
                    )
                }
            }
        }catch (e: JSONException){
            e.printStackTrace()
        }
        return null
    }


    fun getBookInfoFromJSON(context: Context, fileName: String): Book {
        val jsonBook: JSONObject
        try {
            jsonBook = JSONObject(getJsonDataFromAsset(context, fileName))

        }catch (e: JSONException){
            e.printStackTrace()
            throw NullPointerException()
        }

        return  Book(
            jsonBook.getString("title"),
            jsonBook.getString("subtitle"),
            jsonBook.getString("isbn13"),
            jsonBook.getString("price"),
            jsonBook.getString("image"),
            jsonBook.getString("authors"),
            jsonBook.getString("publisher"),
            jsonBook.getString("pages"),
            jsonBook.getString("year"),
            jsonBook.getString("rating"),
            jsonBook.getString("desc")
        )
    }

    fun addToList( book: Book, context: Context) {
        val jsonObject = JSONObject(getJsonDataFromAsset(context, "src/main/jsonFiles/BooksList.txt"))
        val booksJSON = jsonObject.getJSONArray("books").put(book.getValuesForList())
        jsonObject.remove("books")
        jsonObject.put("books", booksJSON)

        writeFile(jsonObject, context)
    }

    private fun writeFile(jsonObject: JSONObject, context: Context) {
        File("app/src/main/jsonFiles").writeText(jsonObject.toString())
    }


}