package us.grahn.trojanow.presentation.home;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Outline;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.Collections;
import java.util.List;

import us.grahn.trojanow.R;
import us.grahn.trojanow.data.Post;
import us.grahn.trojanow.logic.AuthenticationManager;
import us.grahn.trojanow.logic.PostManager;
import us.grahn.trojanow.presentation.authentication.AuthenticationActivity;
import us.grahn.trojanow.presentation.post.create.CreatePostActivity;
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
            if(posts != null && !posts.isEmpty()) {

                Log.i("Posts", "Displaying Posts");

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


                ft.commitAllowingStateLoss();
            } else {
                Log.e("Posts", "Error Retrieving Posts");
            }

            swipe.setRefreshing(false);
        }
    }

    private Menu menu;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_login) {
            login(null);
        } else if(item.getItemId() == R.id.action_logout) {
            getAddPostButton().setVisibility(ImageButton.GONE);

            AccountManager manager = AccountManager.get(this);
            Account[] accounts = manager.getAccountsByType("us.grahn.trojanow");

            for(Account account : accounts) {
                manager.removeAccount(account, null, null);
            }

        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Opens the authentication view.
     *
     * @param view the view which called this method
     */
    public void login(View view) {
        startActivity(new Intent(this, AuthenticationActivity.class));
    }

    /**
     * Opens the create post view.
     *
     * @param view the view which called this method
     */
    public void createPost(View view) {
        startActivity(new Intent(this, CreatePostActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load Views
        this.swipe = (SwipeRefreshLayout) findViewById(R.id.posts_refresh);
        this.postsLayout = (LinearLayout) findViewById(R.id.posts_layout);

        // Floating Action Button
        ImageButton button = (ImageButton) findViewById(R.id.add_button);
        button.setOutlineProvider(new ViewOutlineProvider() {

            @Override
            public void getOutline(View view, Outline outline) {
                int diameter = getResources().getDimensionPixelSize(R.dimen.round_button_diameter);
                outline.setOval(0, 0, diameter, diameter);
            }
        });

        // Load the icon
        getActionBar().setIcon(R.drawable.icon);

        // Load the posts
        swipe.setRefreshing(true);

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
    protected void onStart() {
        super.onStart();

        new LoadPostsTask().execute();

        if(AuthenticationManager.I.isLoggedIn(this)) {
            Log.i("PostButton", "Showing...");
            getAddPostButton().setVisibility(ImageButton.VISIBLE);
        } else {
            Log.i("PostButton", "Hiding...");
            getAddPostButton().setVisibility(ImageButton.GONE);
        }
    }

    protected ImageButton getAddPostButton() {
        return (ImageButton) findViewById(R.id.add_button);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem login = (MenuItem) menu.findItem(R.id.action_login);
        MenuItem logout = (MenuItem) menu.findItem(R.id.action_logout);

        if(AuthenticationManager.I.isLoggedIn(this)) {
            logout.setVisible(true);
            login.setVisible(false);
        } else {
            logout.setVisible(false);
            login.setVisible(true);
        }

        return true;
    }

}
