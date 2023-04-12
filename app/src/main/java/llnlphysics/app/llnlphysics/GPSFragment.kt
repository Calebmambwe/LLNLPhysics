package llnlphysics.app.llnlphysics

import androidx.fragment.app.FragmentActivity
import llnlphysics.app.llnlphysics.databinding.FragmentGyroscopeBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest

class GPSFragment: ThreeAxisBaseExperimentFragment<FragmentGyroscopeBinding>(FragmentGyroscopeBinding::inflate),
    LocationExperiment {

    override val sensorTag: String
        get() = "GPS"
    override var fusedLocationClient: FusedLocationProviderClient? = null
    override var locationRequest: LocationRequest? = null
    override var locationCallback: LocationCallback? = null
    override var fragActivity: FragmentActivity? = null

}