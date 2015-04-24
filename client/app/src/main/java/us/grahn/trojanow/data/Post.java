package us.grahn.trojanow.data;

import android.text.format.DateUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * A data object storing information on posts. Will be populated by the
 * <code>PostManager</code>.
 *
 * @us.grahn.class     Post
 * @us.grahn.component Elemental
 * @us.grahn.tier      Data
 */
public class Post implements Serializable {

    private final List<Emotion> emotions = new ArrayList<Emotion>();
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
     * Adds an emotion to the post.
     *
     * @param emotion the emotion for the post
     */
    public void addEmotion(Emotion emotion) {
        this.emotions.add(emotion);
    }

    /**
     * Gets the author of the post.
     *
     * @return the Iauthor of the post
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
     * Gets a human readable time.
     *
     * @return the time in a human readable format
     */
    public String getReadableTime() {
        final long then = timestamp.getTime() + TimeZone.getDefault().getOffset(timestamp.getTime());
        final long now  = System.currentTimeMillis();
        return DateUtils.getRelativeTimeSpanString(then, now, DateUtils.SECOND_IN_MILLIS).toString();
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
     * Sets the text for the post.
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the timestamp of the post.
     *
     * @return the timestamp of the post
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp for the post.
     *
     * @param timestamp
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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
     * Sets the audience for the post.
     *
     * @param audience the audience for the post
     */
    public void setAudience(Audience audience) {}

    /**
     * Gets the environments for the post.
     *
     * @return the environments for the post
     */
    public List<Environment> getEnvironments() {
        return null;
    }

    /**
     * Sets the environments for the post.
     *
     * @param environments the environments for the post
     */
    public void setEnvironments(List<Environment> environments) {}

    /**
     * Gets the emotions for the post.
     *
     * @return the emotions for the post.
     */
    public List<Emotion> getEmotions() {

        List<Emotion> emotions = new ArrayList<Emotion>();

        for(Emotion emotion : this.emotions) {
            if(emotion.getUser().getId() != getAuthor().getId()) {
                emotions.add(emotion);
            }
        }

        return emotions;
    }


    /**
     * Gets the remotions for the post.
     *
     * @return the remotions for the post
     */
    public List<Emotion> getRemotions() {

        List<Emotion> remotions = new ArrayList<Emotion>();

        for(Emotion emotion : emotions) {
            if(emotion.getUser().getId() != getAuthor().getId()) {
                remotions.add(emotion);
            }
        }

        return remotions;
    }

}
