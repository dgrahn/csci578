package us.grahn.trojanow.logic;

import android.util.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import us.grahn.trojanow.data.Environment;
import us.grahn.trojanow.data.Post;
import us.grahn.trojanow.data.Type;

/**
 * A data manager for environements. Handles CRUD operations with the server.
 *
 * @us.grahn.class     EnvironmentManager
 * @us.grahn.component EnvironmentManager
 * @us.grahn.tier      Logic
 */
public class EnvironmentManager extends Manager {

    public static final EnvironmentManager I = new EnvironmentManager();
    private static final String SENSOR = "sensor";
    private static final String READING = "reading";

    /**
     * Constructs a new {@code EnvironmentManager}. Should not be accessible to the public. Use the
     * constant {@link #I}.
     */
    private EnvironmentManager() {

    }

    /**
     * Reads an environment from the server based on the environment's ID.
     *
     * @param id the ID of the environment to retrieve
     * @return the environment retrieved from the server
     */
    public Environment read(String id) {
        return null;
    }

    /**
     * Reads an environment from the local sensor of the specified type.
     *
     * @param sensor the sensor to read
     * @return the environment generated from the sensor reading
     */
    public Environment read(Type sensor) {
        return null;
    }

    public List<Environment> readArray(JsonReader reader, Post post) throws IOException {

        List<Environment> environments = new ArrayList<Environment>();

        reader.beginArray();

        while(reader.hasNext()) {
            reader.beginObject();

            Environment environment = new Environment();
            environment.setPost(post);

            while(reader.hasNext()) {
                String name = reader.nextName();

                if(SENSOR.equals(name)) {
                    environment.setType(Type.get(reader.nextString()));
                } else if(READING.equals(name)) {
                    environment.setReading(reader.nextDouble());
                }
            }

            environments.add(environment);

            reader.endObject();
        }

        reader.endArray();

        return environments;
    }
}
