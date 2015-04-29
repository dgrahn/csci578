package us.grahn.trojanow.logic;

import android.util.JsonReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static final String SHOW = "user/%d.json";
    private static final String ID = "id";
    private static final String GIVEN_NAME = "given_name";
    private static final String SURNAME = "surname";
    private static final String IMAGE = "image";
    private final Map<Integer, User> userCache = new HashMap<Integer, User>();

    /**
     * The {@code UserManager} which should be used.
     */
    public static final UserManager I = new UserManager();

    /**
     * Constructs a new {@code UserManager}. Should not be accessible to the public. Use the
     * constant {@link #I}.
     */
    private UserManager() {

    }

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
    public User read(int id) {

        if(userCache.containsKey(id)) return userCache.get(id);

        try {
            JsonReader reader = Utilities.getReader(SHOW, id);

            reader.beginObject();

            User user = new User();

            while(reader.hasNext()) {
                String name = reader.nextName();

                if (ID.equals(name)) {
                    user.setId(reader.nextInt());
                } else if (GIVEN_NAME.equals(name)) {
                    user.setGivenName(reader.nextString());
                } else if(SURNAME.equals(name)) {
                    user.setSurname(reader.nextString());
                } else if(IMAGE.equals(name)) {
                    user.setImage(reader.nextString());
                }
            }

            reader.close();

            userCache.put(id, user);

            return user;

        } catch(IOException e) {
            return null;
        }
    }

}
