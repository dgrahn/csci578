package us.grahn.trojanow.logic;

import java.util.List;

import us.grahn.trojanow.data.Result;
import us.grahn.trojanow.data.User;

/**
 * A data manager for users. Handles CRUD operations with the server.
 *
 * @us.grahn.class     UserManager
 * @us.grahn.component UserManager
 * @us.grahn.tier      Logic
 */
public class UserManager extends Manager {

    /**
     * Makes the current user and the specified user pals on the server.
     *
     * @param user the user to make pals with
     * @return the result of the transaction with the server
     */
    public Result createPal(User user) {
        return null;
    }

    /**
     * Reads a list of the current users pals.
     *
     * @return the pals of the current user
     */
    public List<User> read() {
        return null;
    }

    /**
     * Reads a user based upon their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user retrieved from the server
     */
    public User read(String id) {
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
