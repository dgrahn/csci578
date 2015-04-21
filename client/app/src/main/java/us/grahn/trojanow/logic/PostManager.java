package us.grahn.trojanow.logic;

import android.util.JsonReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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

    private static URL INDEX_URL = null;
    static {
        try {
            INDEX_URL = new URL("http://69.133.29.104:3000/posts.json");
        } catch(MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    private static final String ID = "id";
    private static final String TEXT = "text";
    private static final String USER_ID = "user_id";
    private static final String CREATED_AT = "created_at";

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
            URLConnection urlConnection = INDEX_URL.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

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
                        User user = new User();
                        user.setId(reader.nextInt());
                        post.setAuthor(user);
                    } else if(CREATED_AT.equals(name)) {
                        try {
                            Date timestamp = FORMAT.parse(reader.nextString());
                            post.setTimestamp(timestamp);
                        } catch(ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }

                reader.endObject();
                posts.add(post);
            }

            reader.endArray();

            return posts;

        } catch(IOException e) {

            e.printStackTrace();
            return null;
        }
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
