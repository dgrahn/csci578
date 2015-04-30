package us.grahn.trojanow.logic;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.ArrayList;
import java.util.List;

import us.grahn.trojanow.data.Type;
import us.grahn.trojanow.presentation.home.HomeScreenActivity;

/**
 * A simplified API for reading sensor data.
 *
 * @us.grahn.class     SensorAPI
 * @us.grahn.component SensorAPI
 * @us.grahn.tier      Logic
 */
public class SensorAPI {

    private static SensorManager getManager() {
        return (SensorManager) HomeScreenActivity.getAppContext().getSystemService(Context.SENSOR_SERVICE);
    }

    /**
     * Get a list of the valid sensors.
     *
     * @return the valid sensors
     */
    public static List<Type> getSensors() {

        List<Type> sensors = new ArrayList<Type>();
        SensorManager manager = getManager();

        for(Type type : Type.values()) {
            Sensor sensor = manager.getDefaultSensor(type.getSensor());
            if(sensor != null) sensors.add(type);
        }

        return sensors;
    }

    /**
     * Reads the specified sensor.
     *
     * @param type the sensor to read
     */
    public static void read(Type type, final SensorEventListener listener) {

        Sensor sensor = getManager().getDefaultSensor(type.getSensor());

        getManager().registerListener(new SensorEventListener() {

            @Override
            public void onSensorChanged(final SensorEvent event) {
                listener.onSensorChanged(event);
                getManager().unregisterListener(this);
            }

            @Override
            public void onAccuracyChanged(final Sensor sensor, final int accuracy) {

            }

        }, sensor, SensorManager.SENSOR_DELAY_UI);
    }
}
