package llnlphysics.app.llnlphysics

import android.hardware.Sensor
import android.hardware.SensorManager
import llnlphysics.app.llnlphysics.databinding.FragmentGyroscopeBinding

class GyroscopeFragment : ThreeAxisBaseExperimentFragment<FragmentGyroscopeBinding>(FragmentGyroscopeBinding::inflate),
    SensorExperiment {

    override val sensorTag: String
        get() = "Gyroscope"
    override val sensorType: Int
        get() = Sensor.TYPE_GYROSCOPE
    override var mSensorManager: SensorManager? = null
    override var mSensor: Sensor? = null
}