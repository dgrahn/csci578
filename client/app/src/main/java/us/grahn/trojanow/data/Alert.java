package us.grahn.trojanow.data;

import java.io.Serializable;

/**
 * A data object storing information on alerts. Will be populated by the <code>AlertManager</code>.
 *
 * @us.grahn.class     Alert
 * @us.grahn.component Elemental
 * @us.grahn.tier      Data
 */
public class Alert implements Serializable {

    /**
     * Gets the post which the alert is for.
     *
     * @return the post which the alert is for
     */
    public Post getPost() { return null; }

    /**
     * Sets the post which the alert is for.
     *
     * @param post the post which the alert is for
     */
    public void setPost(Post post) { }

}
