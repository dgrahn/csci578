package us.grahn.trojanow.logic;

import us.grahn.trojanow.data.Emotion;
import us.grahn.trojanow.data.Post;
import us.grahn.trojanow.data.Result;

/**
 * A data manager for emotions. Handles CRUD operations with the server.
 *
 * @us.grahn.class     EmotionManager
 * @us.grahn.component EmotionManager
 * @us.grahn.tier      Logic
 */
public class EmotionManager extends Manager {

    /**
     * Creates a remotion on a post.
     *
     * @param post the post to remote on
     * @param emotion the remotion to make
     * @return the result of the transaction with the server
     */
    public Result remote(Post post, Emotion emotion) {
        return null;
    }

    /**
     * Reads an emotion from the server based upon the emotion's ID.
     *
     * @param id the ID of the emotion to retrieve
     * @return the emotion which was read from the server
     */
    public Emotion read(String id) {
        return null;
    }

    @Override
    public Result refresh() {
        return null;
    }

    @Override
    public Result getLastResult() {
        return null;
    }
}
