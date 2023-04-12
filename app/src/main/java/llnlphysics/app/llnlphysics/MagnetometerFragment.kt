package llnlphysics.app.llnlphysics

import android.hardware.Sensor
import android.hardware.SensorManager
import llnlphysics.app.llnlphysics.databinding.FragmentGyroscopeBinding
import llnlphysics.app.llnlphysics.databinding.FragmentMagnetometerBinding

class MagnetometerFragment : ThreeAxisBaseExperimentFragment<FragmentMagnetometerBinding>(FragmentMagnetometerBinding::inflate),
    SensorExperiment {

    override val sensorTag: String
        get() = "Magnetometer"
    override val sensorType: Int
        get() = Sensor.TYPE_MAGNETIC_FIELD
    override var mSensorManager: SensorManager? = null
    override var mSensor: Sensor? = null
}