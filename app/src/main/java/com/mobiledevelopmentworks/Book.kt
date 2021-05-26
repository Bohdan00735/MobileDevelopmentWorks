package com.mobiledevelopmentworks

class Book {
    lateinit var title:String
    lateinit var subtitle:String
    lateinit var isbn13:String
    lateinit var price:String
    lateinit var imagePath:String
    lateinit var authors :String
    lateinit var publisher:String
    lateinit var pages:String
    lateinit var year:String
    lateinit var rating:String
    lateinit var description:String

    constructor(
        title: String,
        subtitle: String,
        isbn13: String,
        price: String,
        imagePath: String,
        authors: String,
        publisher: String,
        pages: String,
        year: String,
        rating: String,
        description: String
    ) {
        this.title = title
        this.subtitle = subtitle
        this.isbn13 = isbn13
        this.price = price
        this.imagePath = imagePath
        this.authors = authors
        this.publisher = publisher
        this.pages = pages
        this.year = year
        this.rating = rating
        this.description = description
    }

    constructor()
    constructor(title: String, subtitle: String, isbn13: String, price: String, imagePath: String) {
        this.title = title
        this.subtitle = subtitle
        this.isbn13 = isbn13
        this.price = price
        this.imagePath = imagePath
        this.authors = ""
        this.publisher = ""
        this.pages = ""
        this.year = ""
        this.rating = ""
        this.description = ""
    }

    fun getValuesForList():String{
        return "{\"title\":$title, \"subtitle\":$subtitle, \"price\":$price, \"isbn13\":$isbn13," +
                "\"image\":$imagePath}"
    }

    fun contain(line : String):Boolean{
        return line in title || line in subtitle
    }

}