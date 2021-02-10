package com.mobiledevelopmentworks

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.ParcelFileDescriptor.open
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.channels.AsynchronousFileChannel.open
import java.nio.channels.AsynchronousServerSocketChannel.open
import java.nio.channels.AsynchronousSocketChannel.open
import java.nio.channels.FileChannel.open
import java.nio.channels.Pipe.open
import java.util.ArrayList

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Utils {
    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
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
                booksList.add(Book(book.getString("title"),
                    book.getString("subtitle"),
                    book.getString("isbn13"),
                    book.getString("price"),
                    book.getString("image")))
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
}