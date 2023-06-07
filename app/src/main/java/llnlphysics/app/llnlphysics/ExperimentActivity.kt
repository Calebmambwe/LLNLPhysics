package llnlphysics.app.llnlphysics

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import llnlphysics.app.llnlphysics.R
import llnlphysics.app.llnlphysics.databinding.ActivityExperimentBinding

class ExperimentActivity : AppCompatActivity() {


    private lateinit var binding: ActivityExperimentBinding
    private var expFrag: Fragment? = null
    private lateinit var expType: ExperimentType
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experiment)

        val fragmentManager = supportFragmentManager
        val extras = intent.extras
        if (extras == null) {
            expType = ExperimentType.eUnknown
        } else {
            expType = extras.getSerializable("expType") as ExperimentType
        }
        when(expType){
            ExperimentType.eAccelerometer -> expFrag = AccelerometerFragment()
            ExperimentType.eGyroscope -> expFrag = GyroscopeFragment()
            ExperimentType.eMagnetometer -> expFrag = MagnetometerFragment()
            ExperimentType.eBarometer -> expFrag = BarometerFragment()
            ExperimentType.eGPS -> expFrag = GPSFragment()
            else -> {
                expFrag = null
            }
        }
        if(expFrag != null){
            fragmentManager.commit {
                replace(R.id.fragmentContainerView, expFrag!!)
                setReorderingAllowed(true)
                addToBackStack("name")
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    // this event will enable the back
    // function to the button on press
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
    // create an action bar button
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // handle button activities
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        if (id == R.id.action_play) {
            if(expFrag is RenderingExperiment){
                if((expFrag as RenderingExperiment).isRendering){
                    (expFrag as RenderingExperiment).onStopClicked()
                    item.setIcon(R.drawable.ic_play_button)
                }else{
                    (expFrag as RenderingExperiment).onStartClicked()
                    item.setIcon(R.drawable.ic_pause_button)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}