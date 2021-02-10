package com.mobiledevelopmentworks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.mobiledevelopmentworks.R
import com.mobiledevelopmentworks.lab1.coordinatesData.CoordinateBM
import com.mobiledevelopmentworks.lab1.coordinatesData.DirectionConverter

/**
 * A simple [Fragment] subclass.
 * Use the [MoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoreFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.findViewById<EditText>(R.id.degree_input)
        return inflater.inflate(R.layout.fragment_more, container, false)
    }

    fun createSmth(view: View){}
}