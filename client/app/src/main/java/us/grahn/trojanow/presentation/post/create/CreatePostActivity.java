package us.grahn.trojanow.presentation.post.create;

import android.app.Activity;
import android.os.Bundle;

import us.grahn.trojanow.R;

/**
 * An interface which allows the user to create a post.
 *
 * @us.grahn.class     CreatePostInterface
 * @us.grahn.component CreatePostInterface
 * @us.grahn.tier      Presentation
 */
public class CreatePostActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
    }

}
