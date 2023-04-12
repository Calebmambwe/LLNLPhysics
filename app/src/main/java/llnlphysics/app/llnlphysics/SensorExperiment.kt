package llnlphysics.app.llnlphysics

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.fragment.app.FragmentActivity

interface SensorExperiment : Experiment, SensorEventListener {

    var mSensorManager: SensorManager?
    var mSensor: Sensor?
    abstract val sensorType: Int

    override fun setupExperiment(activity: FragmentActivity){
        mSensorManager = activity.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mSensor = mSensorManager!!.getDefaultSensor(sensorType)
    }

    override fun registerListener() {
        mSensorManager?.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun unregisterListener() {
        mSensorManager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {
        addEntries(event.values)
    }
}