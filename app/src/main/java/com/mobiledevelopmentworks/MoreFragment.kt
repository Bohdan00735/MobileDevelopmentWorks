package com.mobiledevelopmentworks

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import com.aghajari.graphview.AXGraphFormula
import com.aghajari.graphview.AXGraphOptions
import com.aghajari.graphview.AXGraphView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos

/**
 * A simple [Fragment] subclass.
 * Use the [MoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoreFragment : Fragment() {
    lateinit var graphView: AXGraphView
    lateinit var pieView:PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_more, container, false)
        graphView = root.findViewById<AXGraphView>(R.id.graph_view)
        pieView = root.findViewById(R.id.pie_view)
        setGraph()
        setPieView()
        root.findViewById<ToggleButton>(R.id.change_graphic_button).setOnCheckedChangeListener{
                _, isChecked ->
            if (isChecked) {
                pieView.visibility = View.VISIBLE
                graphView.visibility = View.INVISIBLE
            } else {
                graphView.visibility = View.VISIBLE
                pieView.visibility = View.INVISIBLE
            }
        }

        return root
    }

    private fun setGraph(){
        val options = AXGraphOptions(activity);

        options.scrollEnabled = true;
        options.drawGridXLines = false
        options.drawGridYLines = false
        options.drawXText = false
        options.drawYText = false
        options.xDividerInterval = PI.toFloat()/4
        options.maxZoom = 6f
        options.minZoom = 0.5f
        graphView.graphOptions = options;

        graphView.addFormula(object : AXGraphFormula() {
            override fun function(x: Float): Float {
                return cos(x)
            }

            override fun isInDomain(x: Float): Boolean {
                return abs(x) <= PI
            }
        })
    }

    private fun setPieView(){
        val  entries = ArrayList<PieEntry>()
        entries.add(PieEntry(45f))
        entries.add(PieEntry(5f))
        entries.add(PieEntry(25f))
        entries.add(PieEntry(25f))
        val colorsList = ArrayList<Int>()
        colorsList.add(Color.CYAN)
        colorsList.add(Color.MAGENTA)
        colorsList.add(Color.YELLOW)
        colorsList.add(Color.GRAY)
        val pieDataSet = PieDataSet(entries, "Pie chart")
        pieDataSet.colors = colorsList
        pieDataSet.sliceSpace = 1f;
        pieDataSet.valueTextColor = Color.DKGRAY
        pieDataSet.valueTextSize = 10f;
        pieView.data = PieData(pieDataSet)
    }

}