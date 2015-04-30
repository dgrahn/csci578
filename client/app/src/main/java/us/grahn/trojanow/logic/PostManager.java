package us.grahn.trojanow.logic;

import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import us.grahn.trojanow.data.Emotion;
import us.grahn.trojanow.data.Environment;
import us.grahn.trojanow.data.Post;
import us.grahn.trojanow.data.Result;
import us.grahn.trojanow.data.User;

/**
 * A data manager for posts. Handles CRUD operations with the server.
 *
 * @us.grahn.class     PostManager
 * @us.grahn.component PostManager
 * @us.grahn.tier      Logic
 */
public class PostManager extends Manager {

    /**
     * The {@code PostManager} which should be used.
     */
    public static final PostManager I = new PostManager();

    private static String INDEX  = "posts.json";
    private static String SINCE  = "posts/since/%d.json";
    private static String CREATE = "posts/create.json";

    private static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private static final String ID = "id";
    private static final String TEXT = "text";
    private static final String USER_ID = "user_id";
    private static final String CREATED_AT = "created_at";
    private static final String EMOTIONS = "emotions";
    private static final String ENVIRONMENTS = "environments";

    /**
     * Constructs a new {@code PostManager}. Should not be accessible to the public. Use the
     * constant {@link #I}.
     */
    private PostManager() {

    }

    /**
     * Creates a post on the server.
     *
     * @param post the post to create on the server
     * @return the result of the transaction with the server
     */
    public Result create(Post post) {

        try {
            List<String> params = new ArrayList<String>();
            params.add("token");
            params.add(AuthenticationManager.I.getToken());
            params.add("text");
            params.add(post.getText());
            params.add("anonymous");
            params.add(Boolean.toString(post.isAnonymous()));

            int i = 0;
            for(Environment environment : post.getEnvironments()) {
                params.add("environment-" + i + "-type");
                params.add(environment.getType().toString());
                params.add("environment-" + i++ + "-reading");
                params.add(Double.toString(environment.getReading()));
            }

            i = 0;
            for(Emotion emotion : post.getEmotions()) {
                params.add("emotion-" + i++);
                params.add(Emotion.Type.get(emotion.getType()).getId());
            }

            JsonReader reader = Utilities.getReaderPost(CREATE, params.toArray(new String[params.size()]));
            return Utilities.getResult(reader);
        } catch(IOException e) {
            e.printStackTrace();
            return Result.SERVER_FAILURE;
        }
    }

    /**
     * Reads the posts which the current use should see on his feed.
     *
     * @return the posts for the users feed
     */
    public List<Post> read() {

        try {
            JsonReader reader = Utilities.getReader(INDEX);
            List<Post> posts = readArray(reader);
            reader.close();
            return posts;
        } catch(IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Gets the posts which have been made since the last ID. May be limited by the server.
     *
     * @param id the ID of the last post retrieved
     * @return the posts made since the ID
     */
    public List<Post> since(int id) {

        try {
            Log.d("Posts", "Retrieving posts since " + id);
            JsonReader reader = Utilities.getReader(SINCE, id);

            Log.d("Posts", "Reading posts since " + id);
            List<Post> posts = readArray(reader);
            reader.close();
            return posts;
        } catch(IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Reads an array of posts from a {@link JsonReader}
     *
     * @param reader the reader from which to read the array
     * @return the array from the reader
     */
    private List<Post> readArray(final JsonReader reader) throws IOException {

        List<Post> posts = new ArrayList<Post>();

        reader.beginArray();

        while(reader.hasNext()) {

            reader.beginObject();

            Post post = new Post();

            while(reader.hasNext()) {
                String name = reader.nextName();

                if(ID.equals(name)) {
                    post.setId(reader.nextInt());
                } else if(TEXT.equals(name)) {
                    post.setText(reader.nextString());
                } else if(USER_ID.equals(name)) {
                    User user = UserManager.I.read(reader.nextInt());
                    post.setAuthor(user);
                } else if(CREATED_AT.equals(name)) {
                    try {
                        Date timestamp = FORMAT.parse(reader.nextString());
                        post.setTimestamp(timestamp);
                    } catch(ParseException e) {
                        Log.wtf("Couldn't parse date", e);
                    }
                } else if(EMOTIONS.equals(name)) {
                    post.setEmotions(EmotionManager.I.readArray(reader, post));
                } else if(ENVIRONMENTS.equals(name)) {
                    post.setEnvironments(EnvironmentManager.I.readArray(reader, post));
                }
            }

            reader.endObject();
            posts.add(post);
        }

        reader.endArray();

        return posts;
    }

    /**
     * Reads posts from the server based upon the ID of the posts.
     *
     * @param ids the IDs of the posts to retrieve
     * @return the posts retrieved from the server
     */
    public List<Post> read(String... ids) {
        return null;
    }

}
