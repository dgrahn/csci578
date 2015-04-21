package us.grahn.trojanow.presentation.post;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import us.grahn.trojanow.R;
import us.grahn.trojanow.data.Emotion;

/**
 * An interface which allows the user to create emotions or remotions for a post.
 *
 * @us.grahn.class     EmotionFragment
 * @us.grahn.component CreatePostInterface, ReadPostInterface
 * @us.grahn.tier      Presentation
 */
public class EmotionFragment extends Fragment {

    private static final String PARAM_EMOTION = "emotion";
    private Emotion emotion;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param emotion the emotion to display
     * @return A new instance of fragment EmotionFragment.
     */
    public static EmotionFragment newInstance(Emotion emotion) {
        EmotionFragment fragment = new EmotionFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARAM_EMOTION, emotion);
        fragment.setArguments(args);
        return fragment;
    }

    public EmotionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            emotion = (Emotion) getArguments().getSerializable(PARAM_EMOTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_emotion, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
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
        public void onFragmentInteraction(Uri uri);
    }

}
