package us.grahn.trojanow.logic;

import android.accounts.AccountManager;
import android.content.Context;
import android.util.JsonReader;

import java.io.IOException;

import us.grahn.trojanow.data.Result;

/**
 * A data manager for authentication. Handles authentication operations with the server.
 *
 * @us.grahn.class     AuthenticationManager
 * @us.grahn.component AuthenticationManager
 * @us.grahn.tier      Logic
 */
public class AuthenticationManager extends Manager {

    private static final String SIGN_IN = "users/sign_in.json";

    public static final AuthenticationManager I = new AuthenticationManager();

    private AuthenticationManager() {

    }

    /**
     * Attempt to login a user.
     *
     * @param username the username with which to login
     * @param password the password with which to login
     * @return the result of the transaction with the server
     */
    public Result login(String username, final String password) {

        try {
            final JsonReader reader =
                    Utilities.getReaderPost(SIGN_IN, "username", username, "password", password);
            Result result = Utilities.getResult(reader);
            reader.close();

            return result;
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
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
    public boolean isLoggedIn(Context context) {

        AccountManager manager = AccountManager.get(context);
        return manager.getAccountsByType("us.grahn.trojanow").length != 0;
    }

}
