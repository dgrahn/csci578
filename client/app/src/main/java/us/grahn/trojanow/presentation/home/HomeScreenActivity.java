package us.grahn.trojanow.presentation.home;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Collections;
import java.util.List;

import us.grahn.trojanow.R;
import us.grahn.trojanow.data.Post;
import us.grahn.trojanow.logic.PostManager;
import us.grahn.trojanow.presentation.post.read.PostFragment;

/**
 * The default interface. Contains tabs which allows the user to switch between the feed, chat,
 * alert, and pal views.
 *
 * @us.grahn.class     HomeScreen
 * @us.grahn.component FeedInterface
 * @us.grahn.tier      Presentation
 */
public class HomeScreenActivity extends Activity {

    private SwipeRefreshLayout swipe = null;
    private LinearLayout postsLayout = null;
    private int lastPostId = -1;

    private class LoadPostsTask extends AsyncTask<Object, Void, List<Post>> {

        @Override
        protected List<Post> doInBackground(Object... params) {

            try {
                return PostManager.I.since(lastPostId);
            } catch(Exception e) {
                e.printStackTrace();
                return Collections.emptyList();
            }
        }

        @Override
        protected void onPostExecute(List<Post> posts) {

            // Skip if there no posts
            if(!posts.isEmpty()) {

                // Add a new layout to the top of the posts
                final LinearLayout refreshLayout = new LinearLayout(HomeScreenActivity.this);
                refreshLayout.setId(View.generateViewId());
                refreshLayout.setOrientation(LinearLayout.VERTICAL);
                postsLayout.addView(refreshLayout, 0);

                // Fill the new layout with the new posts
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                for (Post post : posts) {
                    PostFragment fragment = PostFragment.newInstance(post);
                    ft.add(refreshLayout.getId(), fragment, Integer.toString(post.getId()));
                    lastPostId = post.getId();
                }

                ft.commit();
            }

            swipe.setRefreshing(false);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load Views
        this.swipe = (SwipeRefreshLayout) findViewById(R.id.posts_refresh);
        this.postsLayout = (LinearLayout) findViewById(R.id.posts_layout);

        // Load the icon
        getActionBar().setIcon(R.drawable.icon);

        // Load the posts
        swipe.setRefreshing(true);
        new LoadPostsTask().execute();

        // Refresh Listener
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                swipe.setRefreshing(true);
                new LoadPostsTask().execute();
            }
        });
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
