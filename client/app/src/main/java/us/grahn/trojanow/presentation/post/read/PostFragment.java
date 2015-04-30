package us.grahn.trojanow.presentation.post.read;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import us.grahn.trojanow.R;
import us.grahn.trojanow.data.Emotion;
import us.grahn.trojanow.data.Environment;
import us.grahn.trojanow.data.Post;
import us.grahn.trojanow.presentation.post.EnvironmentFragment;

/**
 * Fragment for displaying posts.
 */
public class PostFragment extends Fragment {

    private static final String POST = "post";
    private Post post;

    private OnFragmentInteractionListener mListener;

    /**
     * Create a new {@code PostFragment}
     *
     * @param  post the post to display
     * @return A new instance of fragment PostFragment.
     */
    public static PostFragment newInstance(Post post) {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putSerializable(POST, post);
        fragment.setArguments(args);
        return fragment;
    }

    public PostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.post = (Post) getArguments().get(POST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_post, container, false);

        final TextView authorView = (TextView) root.findViewById(R.id.post_author);
        final TextView contentView = (TextView) root.findViewById(R.id.post_content);
        final TextView dateView = (TextView) root.findViewById(R.id.post_date);
        final TextView remotionsView = (TextView) root.findViewById(R.id.remotions);
        final LinearLayout remotionsLayout = (LinearLayout) root.findViewById(R.id.remotions_layout);

        // Build Emotions Display
        String emotionsString = "";
        for(Emotion e : post.getEmotions()) emotionsString += e.getType();

        // Build Remotions Display
        String remotionsString = "";
        List<Emotion> remotions = post.getRemotions();
        for(int i = 0; i < remotions.size(); i++) {
            Emotion re = remotions.get(i);
            remotionsString += re.getType() + " " + re.getUser().getDisplayName();
            if(i < remotions.size() - 1) remotionsString += "\n";
        }

        // Build Environments Display
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        for(Environment environment : post.getEnvironments()) {
            EnvironmentFragment fragment = EnvironmentFragment.newInstance(environment);
            ft.add(R.id.environments_layout, fragment, Integer.toString(fragment.getId()));
        }
        ft.commit();

        dateView.setText(post.getReadableTime() + " // " + emotionsString);
        authorView.setText(post.getAuthor().getDisplayName());
        contentView.setText(post.getText());
        remotionsView.setText(remotionsString);

        if(remotions.isEmpty()) remotionsLayout.setVisibility(LinearLayout.GONE);

        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof OnFragmentInteractionListener) {
            this.mListener = (OnFragmentInteractionListener) activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
