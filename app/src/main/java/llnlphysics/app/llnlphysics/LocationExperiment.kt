package llnlphysics.app.llnlphysics

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.*


interface LocationExperiment : Experiment {

    // declare a global variable FusedLocationProviderClient
    var fusedLocationClient: FusedLocationProviderClient?
    // globally declare LocationRequest
    var locationRequest: LocationRequest?
    // globally declare LocationCallback
    var locationCallback: LocationCallback?

    var fragActivity: FragmentActivity?


    override fun setupExperiment(activity: FragmentActivity){
        fragActivity = activity
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        getLocationUpdates()
    }

    private fun getLocationUpdates()
    {
        locationRequest = LocationRequest.Builder(2000).build()
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult){
                p0 ?: return

                if (p0.locations.isNotEmpty()) {
                    // get latest location
                    val location =
                        p0.lastLocation
                    val values = FloatArray(3)
                    values[0] = location?.latitude?.toFloat() ?: 0f
                    values[1] = location?.longitude?.toFloat() ?: 0f
                    values[2] = location?.altitude?.toFloat() ?: 0f
                    Log.d("gps", values[0].toString())
                    addEntries(values)
                }
            }
        }
    }
    //start location updates
    private fun startLocationUpdates() {
        fragActivity?.let {
            if (ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
        }
        locationRequest?.let {
            locationCallback?.let { it1 ->
                fusedLocationClient?.requestLocationUpdates(
                    it,
                    it1,
                    Looper.myLooper() /* Looper */
                )
            }
        }
    }

    // stop location updates
    private fun stopLocationUpdates() {
        locationCallback?.let { fusedLocationClient?.removeLocationUpdates(it) }
    }

    override fun registerListener() {
        startLocationUpdates()
    }

    override fun unregisterListener() {
        stopLocationUpdates()
    }
}