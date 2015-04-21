package us.grahn.trojanow.logic;

import us.grahn.trojanow.data.Environment;
import us.grahn.trojanow.data.Result;
import us.grahn.trojanow.data.Type;

/**
 * A data manager for environements. Handles CRUD operations with the server.
 *
 * @us.grahn.class     EnvironmentManager
 * @us.grahn.component EnvironmentManager
 * @us.grahn.tier      Logic
 */
public class EnvironmentManager extends Manager {

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

    @Override
    public Result refresh() {
        return null;
    }

    @Override
    public Result getLastResult() {
        return null;
    }
}
