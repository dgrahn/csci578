package us.grahn.trojanow.presentation.home;

import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import us.grahn.trojanow.R;
import us.grahn.trojanow.data.Post;
import us.grahn.trojanow.logic.PostManager;
import us.grahn.trojanow.presentation.PostFragment;

/**
 * The default interface. Contains tabs which allows the user to switch between the feed, chat,
 * alert, and pal views.
 *
 * @us.grahn.class     HomeScreen
 * @us.grahn.component FeedInterface
 * @us.grahn.tier      Presentation
 */
public class HomeScreenActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncTask task = new AsyncTask<Object, Void, List<Post>>() {

            @Override
            protected List<Post> doInBackground(Object... params) {

                try {
                    return new PostManager().read();
                } catch(Exception e) {
                    e.printStackTrace();
                    return Collections.emptyList();
                }
            }

            @Override
            protected void onPostExecute(List<Post> posts) {
                TextView numberOfPosts = (TextView) findViewById(R.id.number_of_posts);


                Log.i("Post", "Loading posts 2");
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                for(Post post : posts) {
                    PostFragment fragment = PostFragment.newInstance(post);
                    Log.i("Post", post.getId() + " = " + post.getText());
                    ft.add(R.id.posts_layout, fragment, Integer.toString(post.getId()));
                }

                ft.commit();

                numberOfPosts.setText("Number of Posts: " + posts.size());
            }
        };

        task.execute();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
