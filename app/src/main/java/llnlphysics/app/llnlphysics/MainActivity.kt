package llnlphysics.app.llnlphysics

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var menuList: ArrayList<String>
    lateinit var menuListView: ListView
    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    var adapter: ArrayAdapter<String>? = null

    var maintitle = arrayOf(
        "Accelerometer", "Gyroscope",
        "Magnetometer", "Barometer",
        "GPS"
    )

    var subtitle = arrayOf(
        "Raw 3 Axis Acceleration", "Raw 3 Axis Rotation",
        "Raw 3 Axis Magnetic Fields", "Raw Pressure and Relative Altitude",
        "Lat/Long and other location data"
    )

    var imgid = arrayOf<Int>(
        R.drawable.accelerometer_icon, R.drawable.gyroscope_icon,
        R.drawable.magnetometer_icon, R.drawable.barometer_icon,
        R.drawable.gps_icon
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Physics"

        menuListView = findViewById(R.id.menuList)
        val adapter = MyListAdapter(this, maintitle, subtitle, imgid)
        menuListView.adapter = adapter
        menuListView.setOnItemClickListener { parent, view, position, id ->
            // start your activity by passing the intent
            startActivity(Intent(this, ExperimentActivity::class.java).apply {
                // you can add values(if any) to pass to the next class or avoid using `.apply`
                //not sure yet how to better tie enum to list item besides position
                when (position) {
                    0 -> putExtra("expType", ExperimentType.eAccelerometer)
                    1 -> putExtra("expType", ExperimentType.eGyroscope)
                    2 -> putExtra("expType", ExperimentType.eMagnetometer)
                    3 -> putExtra("expType", ExperimentType.eBarometer)
                    4 -> putExtra("expType", ExperimentType.eGPS)
                }
            })
        }
    }
}