package us.grahn.trojanow.data;

import java.io.Serializable;

/**
 * A data object storing information on users. Will be populated by the
 * <code>UserManager</code>.
 *
 * @us.grahn.class     User
 * @us.grahn.component Elemental
 * @us.grahn.tier      Data
 */
public class User implements Serializable {

    private int id = -1;

    /**
     * Gets the ID for the user. This is used for OAuth2.0 login.
     *
     * @return the ID for the user
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the given name for the user.
     *
     * @return the given name for the user
     */
    public String getGivenName() {
        return null;
    }

    /**
     * Gets the surname for the user.
     *
     * @return the surname for the user
     */
    public String getSurname() {
        return null;
    }

    /**
     * Gets the refresh token for the user. Will only be populated for the user who is logged in.
     * This is used for OAuth2.0 login.
     *
     * @return the refresh token for the user
     */
    public String getRefreshToken() {
        return null;
    }

    /**
     * Gets the display name of the user.
     *
     * @return the display name of the user
     */
    public String getDisplayName() {
        return null;
    }

    /**
     * Gets the last post of the user.
     * @return
     */
    public Post getLastPost() {
        return null;
    }

    /**
     * Sets the ID for the user.
     *
     * @param id the ID for the user
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the given name for the user.
     *
     * @param givenName the given name for the user
     */
    public void setGivenName(String givenName) {}

    /**
     * Sets the surname for the user.
     *
     * @param surname the surname for the user
     */
    public void setSurname(String surname) {}

    /**
     * Sets the refresh token for the user.
     *
     * @param refreshToken the refresh token for the user
     */
    public void setRefreshToken(String refreshToken) {}

    /**
     * Sets the last post for the user.
     *
     * @param lastPost the last post for the user
     */
    public void setLastPost(Post lastPost) {}

}
