package llnlphysics.app.llnlphysics

import android.hardware.Sensor
import android.hardware.SensorManager
import llnlphysics.app.llnlphysics.databinding.FragmentAccelerometerBinding

class AccelerometerFragment : ThreeAxisBaseExperimentFragment<FragmentAccelerometerBinding>(FragmentAccelerometerBinding::inflate),
    SensorExperiment {

    override val sensorTag: String
        get() = "Accelerometer"
    override val sensorType: Int
        get() = Sensor.TYPE_ACCELEROMETER
    override var mSensorManager: SensorManager? = null
    override var mSensor: Sensor? = null
}