package us.grahn.trojanow.logic;

import android.media.Image;

import us.grahn.trojanow.data.User;

/**
 * A data manager for pictures. Handles CRUD operations with the server.
 *
 * @us.grahn.class     PictureManager
 * @us.grahn.component PictureManager
 * @us.grahn.tier      Logic
 */
public class PictureManager extends Manager {

    /**
     * Reads the picture from the server for the specified user.
     *
     * @param user the user to retrieve the picture for
     * @return the picture for the user
     */
    public Image read(User user) {
        return null;
    }

}
