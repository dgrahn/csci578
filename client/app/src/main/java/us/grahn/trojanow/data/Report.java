package us.grahn.trojanow.data;

import java.io.Serializable;

/**
 * A data object storing information on reports. Will be populated by the
 * <code>ReportsManager</code>.
 *
 * @us.grahn.class     Report
 * @us.grahn.component Elemental
 * @us.grahn.tier      Data
 */
public class Report implements Serializable {

    /**
     * Gets the reason for the report.
     *
     * @return the reason for the report
     */
    public int getReason() {
        return -1;
    }

    /**
     * Gets the text of the report.
     *
     * @return the text of the report
     */
    public String getText() {
        return null;
    }

    /**
     * Gets the post the report is for.
     *
     * @return the post the report is for
     */
    public Post getPost() {
        return null;
    }

    /**
     * Sets the reason for the report.
     *
     * @param reason the reason for the report
     */
    public void setReason(int reason) {}

    /**
     * Sets the text for the report.
     *
     * @param text the text for the report
     */
    public void setText(String text) {}

    /**
     * Sets the post the report is for.
     *
     * @param post the post the report is for
     */
    public void setPost(Post post) {}

}
