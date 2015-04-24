package us.grahn.trojanow.data;

import android.graphics.Bitmap;

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
    private String givenName = null;
    private String surname = null;
    private String image = null;

    /**
     * Gets the ID for the user. This is used for OAuth2.0 login.
     *
     * @return the ID for the user
     */
    public int getId() {
        return id;
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
     * Gets the given name for the user.
     *
     * @return the given name for the user
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Sets the given name for the user.
     *
     * @param givenName the given name for the user
     */
    public void setGivenName(String givenName) { this.givenName = givenName; }

    /**
     * Gets the surname for the user.
     *
     * @return the surname for the user
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname for the user.
     *
     * @param surname the surname for the user
     */
    public void setSurname(String surname) { this.surname = surname; }

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
     * Sets the refresh token for the user.
     *
     * @param refreshToken the refresh token for the user
     */
    public void setRefreshToken(String refreshToken) {}

    /**
     * Gets the display name of the user.
     *
     * @return the display name of the user
     */
    public String getDisplayName() {
        return givenName + " " + surname;
    }

    /**
     * Gets the image for the user.
     *
     * @return the image for the user
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image for the user.
     *
     * @param image the image for the user
     */
    public void setImage(String image) {
        this.image = image;
    }

}
