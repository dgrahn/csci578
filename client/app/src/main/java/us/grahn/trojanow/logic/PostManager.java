package us.grahn.trojanow.logic;

import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private static String INDEX = "posts.json";
    private static String SINCE = "posts/since/%d.json";

    private static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private static final String ID = "id";
    private static final String TEXT = "text";
    private static final String USER_ID = "user_id";
    private static final String CREATED_AT = "created_at";

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
        return null;
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
            return null;
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
            JsonReader reader = Utilities.getReader(SINCE, id);
            List<Post> posts = readArray(reader);
            reader.close();
            return posts;
        } catch(IOException e) {
            e.printStackTrace();
            return null;
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

    @Override
    public Result refresh() {
        return null;
    }

    @Override
    public Result getLastResult() {
        return null;
    }
}
