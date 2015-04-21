package us.grahn.trojanow.presentation.alert;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import us.grahn.trojanow.R;
import us.grahn.trojanow.data.Alert;

/**
 * A fragment to display a single alert
 *
 * @us.grahn.class     AlertFragment
 * @us.grahn.component AlertInterface
 * @us.grahn.tier      Presentation
 */
public class AlertFragment extends Fragment {

    private static final String PARAM_ALERT = "alert";

    private Alert alert;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param alert the alert to display
     * @return A new instance of fragment AlertFragment.
     */
    public static AlertFragment newInstance(Alert alert) {
        AlertFragment fragment = new AlertFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARAM_ALERT, alert);
        fragment.setArguments(args);
        return fragment;
    }

    public AlertFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            alert = (Alert) getArguments().getSerializable(PARAM_ALERT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alert, container, false);
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
