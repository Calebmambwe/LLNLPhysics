package llnlphysics.app.llnlphysics

import android.hardware.Sensor
import android.hardware.SensorManager
import llnlphysics.app.llnlphysics.databinding.FragmentBarometerBinding
import llnlphysics.app.llnlphysics.databinding.FragmentGyroscopeBinding

class BarometerFragment : SingleValueBaseExperimentFragment<FragmentBarometerBinding>(FragmentBarometerBinding::inflate),
    SensorExperiment {

    override val sensorTag: String
        get() = "Barometer"
    override val sensorType: Int
        get() = Sensor.TYPE_PRESSURE
    override var mSensorManager: SensorManager? = null
    override var mSensor: Sensor? = null
}