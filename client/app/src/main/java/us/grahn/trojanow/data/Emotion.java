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

    /**
     * Gets the type of emotion.
     *
     * @return the type of emotion
     */
    public int getType() {
        return -1;
    }

    /**
     * Gets the post which this emotion is for.
     *
     * @return the post which this emotion is for
     */
    public Post getPost() {
        return null;
    }

    /**
     * Gets the user who made the emotion.
     *
     * @return the user who made the emotion
     */
    public User getUser() {
        return null;
    }

    /**
     * Sets the type of emotion.
     *
     * @param type the type of emotion
     */
    public void setType(int type) {}

    /**
     * Sets the post which the emotion is for.
     *
     * @param post the post which the emotion is for
     */
    public void setPost(Post post) {}

    /**
     * Sets the user who made the emotion.
     *
     * @param user the user who made the emotion
     */
    public void setUser(User user) {}
}
