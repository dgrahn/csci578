package us.grahn.trojanow.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A data object storing information on posts. Will be populated by the
 * <code>PostManager</code>.
 *
 * @us.grahn.class     Post
 * @us.grahn.component Elemental
 * @us.grahn.tier      Data
 */
public class Post implements Serializable {

    private User author = null;
    private String text = null;
    private Date timestamp = null;
    private int id = -1;

    /**
     * Gets the ID of the post.
     *
     * @return the ID of the post
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the post.
     *
     * @param id the ID of the post
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the author of the post.
     *
     * @return the author of the post
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Sets the author of the post.
     *
     * @return the author of the post
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Gets the text of the post.
     *
     * @return the text of the post
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the timestamp of the post.
     *
     * @return the timestamp of the post
     */
    public Date getTimestamp() {
        return null;
    }

    /**
     * Gets the audience of the post.
     *
     * @return the audience of the post
     */
    public Audience getAudience() {
        return null;
    }

    /**
     * Gets the environments for the post.
     *
     * @return the environments for the post
     */
    public List<Environment> getEnvironments() {
        return null;
    }

    /**
     * Gets the emotions for the post.
     *
     * @return the emotions for the post.
     */
    public List<Emotion> getEmotions() {
        return null;
    }

    /**
     * Gets the remotions for the post.
     *
     * @return the remotions for the post
     */
    public List<Emotion> getRemotions() {
        return null;
    }

    /**
     * Sets the text for the post.
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Sets the timestamp for the post.
     * @param timestamp
     */
    public void setTimestamp(Date timestamp) {}

    /**
     * Sets the audience for the post.
     *
     * @param audience the audience for the post
     */
    public void setAudience(Audience audience) {}

    /**
     * Sets the environments for the post.
     *
     * @param environments the environments for the post
     */
    public void setEnvironments(List<Environment> environments) {}

    /**
     * Sets the emotions for the post.
     *
     * @param emotions the emotions for the post
     */
    public void setEmotions(List<Emotion> emotions) {}

    /**
     * Sets the remotions for the post
     *
     * @param remotions the remotions for the post
     */
    public void setRemotions(List<Emotion> remotions) {}

}
