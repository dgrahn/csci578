package us.grahn.trojanow.data;

import java.io.Serializable;
import java.util.List;

/**
 * A data object storing information on audiences. Will be populated by the
 * <code>AudienceManager</code>.
 *
 * @us.grahn.class     Audience
 * @us.grahn.component Elemental
 * @us.grahn.tier      Data
 */
public class Audience implements Serializable {

    /**
     * Gets the users who are in the audience.
     *
     * @return the users who are in the audience
     */
    public List<User> getUsers() {
        return null;
    }

    /**
     * Gets the post which the audience is for.
     *
     * @return the post the audience is for
     */
    public Post getPost() {
        return null;
    }

    /**
     * Sets the user who are in the audience.
     *
     * @param users the users who are in the audience
     */
    public void setUsers(List<User> users) {}

    /**
     * Sets the post which the audience is for.
     *
     * @param post the post which the audience is for
     */
    public void setPost(Post post) {}
}
