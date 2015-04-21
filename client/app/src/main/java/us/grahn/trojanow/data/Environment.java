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

    /**
     * Gets the type of sensor which obtained the reading.
     *
     * @return the type of sensor which obtained the reading
     */
    public Type getType() {
        return null;
    }

    /**
     * Gets the reading from the sensor.
     *
     * @return the reading from the sensor
     */
    public long[] getReading() {
        return null;
    }

    /**
     * Gets the post for this environment.
     *
     * @return the post for the environment
     */
    public Post getPost() {
        return null;
    }

    /**
     * Sets the type of sensor which obtained the reading.
     *
     * @param type the type of sensor which obtained the reading.
     */
    public void setType(Type type) {}

    /**
     * Sets the reading from the sensor.
     *
     * @param reading the reading from the sensor
     */
    public void setReading(long[] reading) {}

    /**
     * Sets the post which this environment is for.
     *
     * @param post the post which this environment is for
     */
    public void setPost(Post post) {}

}
