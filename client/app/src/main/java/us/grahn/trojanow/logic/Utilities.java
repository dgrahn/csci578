package us.grahn.trojanow.logic;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.JsonReader;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import us.grahn.trojanow.R;
import us.grahn.trojanow.data.Result;

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
     * Posts values to a URL and returns the {@link JsonReader}
     * @param path
     * @param args
     * @return
     * @throws IOException
     */
    public static JsonReader getReaderPost(final String path, final String... args) throws IOException {

        if(args.length % 2 != 0) throw new IllegalArgumentException("Invalid key/value combo.");

        // Build the post
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(getURL(path).toExternalForm());

        // Build the ARgs
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        for(int i = 0; i < args.length; i += 2) {
            nameValuePairs.add(new BasicNameValuePair(args[i], args[i + 1]));
        }

        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

        // Issue the command / Get the response
        HttpResponse response = httpClient.execute(httpPost);
        InputStream in = response.getEntity().getContent();
        return new JsonReader(new InputStreamReader(in, "UTF-8"));
    }

    /**
     * Show's a dialog with an OK button.
     *
     * @param context the context for the dialog
     * @param title   the title for the dialog
     * @param message the message for the dialog
     */
    public static void showDialog(final Context context, final int title, final int message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setPositiveButton(R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Shows a dialog from a result.
     *
     * @param context the context for the dialog
     * @param result the result for the dialog
     */
    public static void showDialog(final Context context, final Result result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(result.getMessage());
        builder.setTitle(result.getTitle());
        builder.setPositiveButton(R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Reads a result from a JsonReader. Closes the reader.
     *
     * @param reader the reader from which to read the result
     * @return the result
     * @throws IOException if there is an exception in the reader
     */
    public static Result getResult(JsonReader reader) throws IOException {

        reader.beginObject();

        Result result = new Result();

        while(reader.hasNext()) {
            String name = reader.nextName();

            if("code".equals(name)) {
                result.setCode(reader.nextInt());
            } else if("message".equals(name)) {
                result.setMessage(reader.nextString());
            }
        }

        reader.endObject();
        reader.close();

        return result;
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
