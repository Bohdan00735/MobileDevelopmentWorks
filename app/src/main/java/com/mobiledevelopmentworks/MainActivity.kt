package com.mobiledevelopmentworks

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobiledevelopmentworks.lab1.coordinatesData.CoordinateBM
import com.mobiledevelopmentworks.lab1.coordinatesData.DirectionConverter

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {
    private lateinit var coordinate:CoordinateBM

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        actionBar?.hide();
        supportActionBar?.hide()

        val navController = findNavController(R.id.container)
        navView.setupWithNavController(navController)
    }

    fun saveCoordinate(view: View){
        val degree = findViewById<EditText>(R.id.degree_input).text.toString().toInt()
        val minutes = findViewById<EditText>(R.id.minutes_input).text.toString().toUInt()
        val seconds = findViewById<EditText>(R.id.seconds_import).text.toString().toUInt()
        val direction = findViewById<EditText>(R.id.dirrection_input).text.toString()
        coordinate = CoordinateBM(DirectionConverter().getDirection(direction)!!, degree, minutes,seconds)
    }

    fun getCoordinate(view: View){
        findViewById<TextView>(R.id.result_view).append(coordinate.getCoordinate())
    }

    fun getDecimalCoordinate(view: View){
        findViewById<TextView>(R.id.result_view).append(coordinate.getDecimalCoordinate())
    }

    fun calculateMiddleOfTwoCoordinates(view: View){
        var degree = findViewById<EditText>(R.id.degree_input1).text.toString().toInt()
        var minutes =findViewById<EditText>(R.id.minutes_input1).text.toString().toUInt()
        var seconds = findViewById<EditText>(R.id.seconds_import1).text.toString().toUInt()
        var direction = findViewById<EditText>(R.id.dirrection_input1).text.toString()
        val coordinate1 = CoordinateBM(DirectionConverter().getDirection(direction)!!, degree, minutes,seconds)
        degree = findViewById<EditText>(R.id.degree_input2).text.toString().toInt()
        minutes = findViewById<EditText>(R.id.minutes_input2).text.toString().toUInt()
        seconds = findViewById<EditText>(R.id.seconds_import2).text.toString().toUInt()
        direction = findViewById<EditText>(R.id.dirrection_input2).text.toString()
        val coordinate2 = CoordinateBM(DirectionConverter().getDirection(direction)!!, degree, minutes,seconds)

        findViewById<TextView>(R.id.result_view).append(
                CoordinateBM().calculateMediumCoordinateFromInput(coordinate1,coordinate2)?.
                getCoordinate().toString())
    }
}