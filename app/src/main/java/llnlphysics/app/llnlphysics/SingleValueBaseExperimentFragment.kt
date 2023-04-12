package llnlphysics.app.llnlphysics

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

abstract class SingleValueBaseExperimentFragment<V: ViewBinding>(
    private val bindingInflater: (inflate: LayoutInflater) -> V)
    : BaseExperimentFragment<V>(bindingInflater)
{
    internal var chart: LineChart? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chart = view.findViewById<LineChart>(R.id.chart)
        initChart(chart!!, "$sensorTag")

        val mStringSpan = SpannableStringBuilder("a(m/s2)")
        mStringSpan.setSpan(SuperscriptSpan(), 5, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Setting the text size ratio for "6" with
        // respect to rest of the span
        mStringSpan.setSpan(RelativeSizeSpan(0.5f),5, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        view.findViewById<TextView>(R.id.chartyaxis).text = mStringSpan
        view.findViewById<TextView>(R.id.chartxaxis).text = "t(s)"
    }

    override fun onStartClicked(){
        chart?.data?.clearValues()
        isRendering = true
        feedMultiple()
    }


    override fun addEntries(values: FloatArray) {
        val data1: LineData? = chart?.data
        if(addEntry(values[0], data1, R.color.seriescolor1)){
            // let the chart know it's data has changed
            chart?.notifyDataSetChanged()

            // limit the number of visible entries
            chart?.setVisibleXRangeMaximum(150f)
            // mChart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            chart?.moveViewToX(data1?.entryCount?.toFloat() ?: 0f)
        }
    }
}