package us.grahn.trojanow.logic;

import us.grahn.trojanow.data.Result;

/**
 * A data manager for authentication. Handles authentication operations with the server.
 *
 * @us.grahn.class     AuthenticationManager
 * @us.grahn.component AuthenticationManager
 * @us.grahn.tier      Logic
 */
public class AuthenticationManager extends Manager {

    /**
     * Attempt to login a user.
     *
     * @param userId       the user id of the user to login
     * @param refreshToken the refresh token of the user to login
     * @return the result of the transaction with the server
     */
    public Result login(String userId, String refreshToken) {
        return null;
    }

    /**
     * Attempt to logout a user.
     *
     * @return the result of the transaction with the server
     */
    public Result logout() {
        return null;
    }

    /**
     * Checks if the current user is logged in.
     *
     * @return the result of the transaction with the server
     */
    public Result isLoggedIn() {
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
