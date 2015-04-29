package us.grahn.trojanow.logic;

import java.util.List;

import us.grahn.trojanow.data.Alert;
import us.grahn.trojanow.data.Result;
import us.grahn.trojanow.data.User;

/**
 * A data manager for alerts. Handles CRUD operations with the server.
 *
 * @us.grahn.class     AlertManager
 * @us.grahn.component AlertManager
 * @us.grahn.tier      Logic
 */
public class AlertManager extends Manager {

    /**
     * Subscribes the current user to the passed in user. After subscription the current user will
     * receive alerts for the user they subscribed to.
     *
     * @param user the user to subscribe to
     * @return the result of the transaction with the server
     */
    public Result subscribe(User user) {
        return null;
    }

    /**
     * Reads the alerts for the current user.
     *
     * @return the alerts for the current user
     */
    public List<Alert> read() { return null; }

}
