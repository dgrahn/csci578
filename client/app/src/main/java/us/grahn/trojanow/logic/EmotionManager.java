package us.grahn.trojanow.logic;

import android.util.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private static final String USER_ID = "user_id";
    private static final String EMOTION = "emotion";

    public static final EmotionManager I = new EmotionManager();

    /**
     * Constructs a new {@code EmotionManager}. Should not be accessible to the public. Use the
     * constant {@link #I}.
     */
    private EmotionManager() {

    }

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
     * Reads an array of emotions.
     *
     * @param reader the reader from which to read the emotions
     * @return the emotions from the reader
     * @throws IOException
     */
    public List<Emotion> readArray(JsonReader reader, Post post) throws IOException {

        List<Emotion> emotions = new ArrayList<Emotion>();

        reader.beginArray();

        while(reader.hasNext()) {
            reader.beginObject();

            Emotion emotion = new Emotion();
            emotion.setPost(post);

            while(reader.hasNext()) {
                String name = reader.nextName();

                if(USER_ID.equals(name)) {
                    emotion.setUser(UserManager.I.read(reader.nextInt()));
                } else if(EMOTION.equals(name)) {
                    emotion.setType(reader.nextString());
                }
            }

            emotions.add(emotion);

            reader.endObject();
        }

        reader.endArray();

        return emotions;
    }

}
