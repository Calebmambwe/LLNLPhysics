package llnlphysics.app.llnlphysics

import android.hardware.SensorEvent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan
import android.text.style.SuperscriptSpan
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import llnlphysics.app.llnlphysics.R

abstract class ThreeAxisBaseExperimentFragment<V: ViewBinding>(
    private val bindingInflater: (inflate: LayoutInflater) -> V)
    : BaseExperimentFragment<V>(bindingInflater)
{
    internal var chart1: LineChart? = null
    internal var chart2: LineChart? = null
    internal var chart3: LineChart? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chart1 = view.findViewById<LineChart>(R.id.chart1)
        initChart(chart1!!, "$sensorTag X")
        chart2 = view.findViewById<LineChart>(R.id.chart2)
        initChart(chart2!!, "$sensorTag Y")
        chart3 = view.findViewById<LineChart>(R.id.chart3)
        initChart(chart3!!, "$sensorTag Z")

        val mStringSpan = SpannableStringBuilder("a(m/s2)")
        mStringSpan.setSpan(SuperscriptSpan(), 5, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Setting the text size ratio for "6" with
        // respect to rest of the span
        mStringSpan.setSpan(RelativeSizeSpan(0.5f),5, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        view.findViewById<TextView>(R.id.chart1yaxis).text = mStringSpan
        view.findViewById<TextView>(R.id.chart1xaxis).text = "t(s)"

        view.findViewById<TextView>(R.id.chart2yaxis).text = mStringSpan
        view.findViewById<TextView>(R.id.chart2xaxis).text = "t(s)"

        view.findViewById<TextView>(R.id.chart3yaxis).text = mStringSpan
        view.findViewById<TextView>(R.id.chart3xaxis).text = "t(s)"
    }

    override fun onStartClicked(){
        chart1?.data?.clearValues()
        chart2?.data?.clearValues()
        chart3?.data?.clearValues()
        isRendering = true
        registerListener()
        feedMultiple()
    }


    override fun addEntries(values: FloatArray) {
        val data1: LineData? = chart1?.data
        val data2: LineData? = chart2?.data
        val data3: LineData? = chart3?.data
        if(addEntry(values[0], data1, R.color.seriescolor1)){
            // let the chart know it's data has changed
            chart1?.notifyDataSetChanged()

            // limit the number of visible entries
            chart1?.setVisibleXRangeMaximum(150f)
            // mChart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            chart1?.moveViewToX(data1?.entryCount?.toFloat() ?: 0f)
        }
        if(addEntry(values[1], data2, R.color.seriescolor2)){
            // let the chart know it's data has changed
            chart2?.notifyDataSetChanged()

            // limit the number of visible entries
            chart2?.setVisibleXRangeMaximum(150f)
            // mChart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            chart2?.moveViewToX(data2?.entryCount?.toFloat() ?: 0f)
        }
        if(addEntry(values[2], data3, R.color.seriescolor3)){
            // let the chart know it's data has changed
            chart3?.notifyDataSetChanged()

            // limit the number of visible entries
            chart3?.setVisibleXRangeMaximum(150f)
            // mChart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            chart3?.moveViewToX(data1?.entryCount?.toFloat() ?: 0f)
        }
    }
}