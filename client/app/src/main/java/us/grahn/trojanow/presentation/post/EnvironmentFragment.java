package us.grahn.trojanow.presentation.post;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import us.grahn.trojanow.R;
import us.grahn.trojanow.data.Environment;

/**
 * Fragment for displaying environments.
 */
public class EnvironmentFragment extends Fragment {

    private static final String ENVIRONMENT = "environment";
    private Environment environment;

    public static EnvironmentFragment newInstance(Environment environment) {
        EnvironmentFragment fragment = new EnvironmentFragment();
        Bundle args = new Bundle();
        args.putSerializable(ENVIRONMENT, environment);
        fragment.setArguments(args);
        return fragment;
    }

    public EnvironmentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        environment = (Environment) getArguments().getSerializable(ENVIRONMENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_environment, container, false);

        TextView type = (TextView) root.findViewById(R.id.environment_type);
        TextView reading = (TextView) root.findViewById(R.id.environment_reading);

        type.setText(environment.getType().toString());
        reading.setText(environment.getHumanReading());

        return root;
    }

}
