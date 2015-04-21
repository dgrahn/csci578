package us.grahn.trojanow.logic;

import us.grahn.trojanow.data.Result;

/**
 * A super class for data managers. Data managers handle CRUD operations with the server. They will
 * generally return the type of
 *
 * @us.grahn.class     Manager
 * @us.grahn.component All Manager
 * @us.grahn.tier      Presentation
 */
public abstract class Manager {

    /**
     * Forces synchronization of data with the server which may be in a holding pattern.
     *
     * @return the result of the transaction with the server
     */
    public abstract Result refresh();

    /**
     * Gets the result of the last transaction with the server
     *
     * @return the result of the last transaction with the server
     */
    public abstract Result getLastResult();

}
