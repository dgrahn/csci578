package us.grahn.trojanow.logic;

import us.grahn.trojanow.data.Audience;
import us.grahn.trojanow.data.User;

/**
 * A data manager for audiences. Handles CRUD operations with the server.
 *
 * @us.grahn.class     AudienceManager
 * @us.grahn.component AudienceManager
 * @us.grahn.tier      Logic
 */
public class AudienceManager extends Manager {

    /**
     * Reads an audience from the server based upon the ID of the audience.
     *
     * @param id the ID of the audience to retrieve
     * @return the audience retrieved from the server
     */
    public Audience read(String id) {
        return null;
    }

    /**
     * Creates an audience based on the passed in users. Will not create the audience on the server
     * since the audience will be sent along with the new post.
     *
     * @param users the users to make up the audience
     * @return the audience which was created
     */
    public Audience create(User... users) {
        return null;
    }

}
