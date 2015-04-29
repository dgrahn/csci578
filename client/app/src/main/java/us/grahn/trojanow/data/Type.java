package us.grahn.trojanow.data;

/**
 * A enumeration storing information on environment types.
 *
 * @us.grahn.class     Type
 * @us.grahn.component Elemental
 * @us.grahn.tier      Logic
 */
public enum Type {

    AMBIENT_TEMPERATURE("Ambient Temperature"), // Celsius
    LIGHT("Light"),                             // Lux
    PRESSURE("Pressure"),                       // Millibars
    RELATIVE_HUMIDITY("Relative Humidity");     // Percentage

    private final String value;

    private Type(String value) {
        this.value = value;
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
}
