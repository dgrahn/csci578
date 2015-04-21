package us.grahn.trojanow.presentation;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import us.grahn.trojanow.R;
import us.grahn.trojanow.data.Post;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PostFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PostFragment#newInstance} factory method to
 * create an instance of this fragment.
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

        TextView author = (TextView) root.findViewById(R.id.author);
        TextView content = (TextView) root.findViewById(R.id.content);
        author.setText(Integer.toString(post.getAuthor().getId()));
        content.setText(post.getText());

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
