package us.grahn.trojanow.data;

import android.hardware.Sensor;

/**
 * A enumeration storing information on environment types.
 *
 * @us.grahn.class     Type
 * @us.grahn.component Elemental
 * @us.grahn.tier      Logic
 */
public enum Type {

    AMBIENT_TEMPERATURE("Ambient Temperature", "C", Sensor.TYPE_AMBIENT_TEMPERATURE),
    LIGHT("Light", "Lux", Sensor.TYPE_LIGHT),
    PRESSURE("Pressure", "Mb", Sensor.TYPE_PRESSURE),
    RELATIVE_HUMIDITY("Relative Humidity", "%", Sensor.TYPE_RELATIVE_HUMIDITY);

    private final String value;
    private final int sensor;
    private final String unit;

    private Type(String value, final String unit, int sensor) {
        this.value = value;
        this.unit = unit;
        this.sensor = sensor;
    }

    public String toString() {
        return value;
    }

    public static Type get(final String value) {
        for(Type type : Type.values()) {
            if(type.value.equals(value)) return type;
        }

        return null;
    }

    public String getUnit() {
        return unit;
    }

    public int getSensor() {
        return sensor;
    }
}
