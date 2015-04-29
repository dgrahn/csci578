package us.grahn.trojanow.data;

import java.io.Serializable;

/**
 * A data object storing information on environments. Will be populated by the
 * <code>EnvironmentManager</code>.
 *
 * @us.grahn.class     Environment
 * @us.grahn.component Elemental
 * @us.grahn.tier      Data
 */
public class Environment implements Serializable {

    private Post post = null;
    private Type type = null;
    private double reading = -1;

    /**
     * Gets the type of sensor which obtained the reading.
     *
     * @return the type of sensor which obtained the reading
     */
    public Type getType() {
        return type;
    }

    /**
     * Gets the reading from the sensor.
     *
     * @return the reading from the sensor
     */
    public double getReading() {
        return reading;
    }

    /**
     * Gets the human readable reading for the environment
     *
     * @return the human readable reading
     */
    public String getHumanReading() {
        return Double.toString(reading);
    }

    /**
     * Gets the post for this environment.
     *
     * @return the post for the environment
     */
    public Post getPost() {
        return post;
    }

    /**
     * Sets the type of sensor which obtained the reading.
     *
     * @param type the type of sensor which obtained the reading.
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Sets the reading from the sensor.
     *
     * @param reading the reading from the sensor
     */
    public void setReading(double reading) {
        this.reading = reading;
    }

    /**
     * Sets the post which this environment is for.
     *
     * @param post the post which this environment is for
     */
    public void setPost(Post post) {
        this.post = post;
    }

}
