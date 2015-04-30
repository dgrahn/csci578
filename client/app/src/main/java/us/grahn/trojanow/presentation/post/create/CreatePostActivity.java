package us.grahn.trojanow.presentation.post.create;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import us.grahn.trojanow.R;
import us.grahn.trojanow.data.Emotion;
import us.grahn.trojanow.data.Environment;
import us.grahn.trojanow.data.Post;
import us.grahn.trojanow.data.Result;
import us.grahn.trojanow.data.Type;
import us.grahn.trojanow.logic.PostManager;
import us.grahn.trojanow.logic.SensorAPI;
import us.grahn.trojanow.logic.Utilities;
import us.grahn.trojanow.presentation.post.EnvironmentFragment;

/**
 * An interface which allows the user to create a post.
 *
 * @us.grahn.class     CreatePostInterface
 * @us.grahn.component CreatePostInterface
 * @us.grahn.tier      Presentation
 */
public class CreatePostActivity extends Activity {

    private List<Environment> environments = new ArrayList<Environment>();
    private List<Emotion> emotions = new ArrayList<Emotion>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
    }

    private class AddSensorListener implements SensorEventListener {

        private final Type type;

        public AddSensorListener(Type type) {
            this.type = type;
        }

        @Override
        public void onSensorChanged(final SensorEvent event) {

            // Create environment
            Environment environment = new Environment();
            environment.setType(type);
            environment.setReading(event.values[0]);
            environments.add(environment);

            // Create fragment
            EnvironmentFragment fragment = EnvironmentFragment.newInstance(environment);

            // Add fragment to view
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.create_post_environments, fragment, Integer.toString(fragment.getId()));
            ft.commit();

            Log.i("Environment", "Environment Added = " + environment.toString());
        }

        @Override
        public void onAccuracyChanged(final Sensor sensor, final int accuracy) {

        }
    }

    public void addEmotion(View view) {

        final TextView emojiView = (TextView) findViewById(R.id.create_post_emotions);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                Emotion emotion = new Emotion();
                emotion.setType(Emotion.ALL[which]);
                emotions.add(emotion);
                emojiView.setText(emojiView.getText() + emotion.getType());
                dialog.dismiss();
            }
        };

        // Remove already added
        List<String> emoji = new ArrayList<String>();
        emoji.addAll(Arrays.asList(Emotion.ALL));

        for(Emotion e : emotions) {
            emoji.remove(e.getType());
        }

        builder.setSingleChoiceItems(emoji.toArray(new String[emoji.size()]), -1, listener);
        builder.setTitle("Select an Emotion");
        builder.setNegativeButton(R.string.cancel, null);
        builder.show();
    }

    public void addSensor(View view) {
        final List<String> sensors = new ArrayList<String>();
        final List<Type> types = SensorAPI.getSensors();

        // Remove existing environments
        for(Environment environment : environments) {
            types.remove(environment.getType());
        }

        for(Type type : types) {
            sensors.add(type.toString());
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                final Type type = types.get(which);
                SensorAPI.read(type, new AddSensorListener(type));
                dialog.dismiss();
            }
        };

        builder.setSingleChoiceItems(sensors.toArray(new String[sensors.size()]), -1, listener);
        builder.setTitle("Select a Sensor");
        builder.setNegativeButton(R.string.cancel, null);
        builder.show();
    }

    /**
     * Create a new post.
     *
     * @param view the view which called the method
     */
    public void post(View view) {

        final String text = ((EditText) findViewById(R.id.create_post_text)).getText().toString();
        final boolean anonymous = ((Switch) findViewById(R.id.create_post_anonymous)).isChecked();

        final Post post = new Post();
        post.setText(text);
        post.setAnonymous(anonymous);
        post.setEnvironments(environments);
        post.setEmotions(emotions);

        new AsyncTask<Void, Void, Result>() {

            @Override
            protected Result doInBackground(final Void... params) {
                return PostManager.I.create(post);
            }

            @Override
            protected void onPostExecute(Result result) {

                if(result.getCode() == Result.POST_SUCCESS) {
                    CreatePostActivity.this.finish();
                } else {
                    Utilities.showDialog(CreatePostActivity.this, result);
                }
            }

        }.execute();

        Log.i("Posts", "Creating Post");
        Log.i("Posts", "         text = " + text);
        Log.i("Posts", "    anonymous = " + anonymous);

    }

}
