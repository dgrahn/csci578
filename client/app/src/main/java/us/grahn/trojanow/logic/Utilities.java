package us.grahn.trojanow.logic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.JsonReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Collection of utility methods.
 *
 * Created by Dan on 4/23/2015.
 */
public class Utilities {

    /**
     * The server where our data is located.
     */
    private static final String SERVER = "http://69.133.29.104:3000/";

    /**
     * Gets a URL. Returns null if the url isn't valid. Prepends the base server path.
     *
     * @param path the path from which to build the URL.
     * @return the URL
     */
    public static URL getURL(final String path) {
        try {
            return new URL(SERVER + path);
        } catch(MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets a URL and interpolate the arguments.
     *
     * @param path the path from which to build the URL.
     * @param args the arguments to interpolate into the path
     * @return the URL
     */
    public static URL getURL(final String path, final Object... args) {
        return getURL(String.format(path, args));
    }

    /**
     * Creates a JSON reader from a URL.
     *
     * @param path the path from which to build the URL.
     * @param args the arguments to interpolate into the path
     * @return the JSON Reader
     * @throws IOException if there is an IO exception or the URL cannot be built.
     */
    public static JsonReader getReader(final String path, final Object... args) throws IOException {

        URL url = getURL(path, args);
        if(url == null) { throw new IOException("Unable to create URL"); }

        URLConnection urlConnection = url.openConnection();
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        return new JsonReader(new InputStreamReader(in, "UTF-8"));
    }

    /**
     * Gets an image from a URL.
     *
     * @param path the URL for the image
     * @return the image
     */
    public static Bitmap getImage(final String path) {

        URL url = getURL(path);
        if(url == null) return null;

        try {
            InputStream stream = url.openStream();
            return BitmapFactory.decodeStream(stream);
        } catch(IOException e) {
            return null;
        }
    }

}
