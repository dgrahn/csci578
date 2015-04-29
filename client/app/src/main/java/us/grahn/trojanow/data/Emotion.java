package us.grahn.trojanow.data;

import java.io.Serializable;

/**
 * A data object storing information on emotions. Will be populated by the
 * <code>EmotionManager</code>.
 *
 * @us.grahn.class     Emotion
 * @us.grahn.component Elemental
 * @us.grahn.tier      Data
 */
public class Emotion implements Serializable {

    private Post post = null;
    private String type = null;
    private User user = null;

    /**
     * Gets the type of emotion.
     *
     * @return the type of emotion
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the post which this emotion is for.
     *
     * @return the post which this emotion is for
     */
    public Post getPost() {
        return post;
    }

    /**
     * Gets the user who made the emotion.
     *
     * @return the user who made the emotion
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the type of emotion.
     *
     * @param type the type of emotion
     */
    public void setType(String type) { this.type = type; }

    /**
     * Sets the post which the emotion is for.
     *
     * @param post the post which the emotion is for
     */
    public void setPost(Post post) { this.post = post; }

    /**
     * Sets the user who made the emotion.
     *
     * @param user the user who made the emotion
     */
    public void setUser(User user) { this.user = user; }
}
