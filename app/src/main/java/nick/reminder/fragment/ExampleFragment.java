package nick.reminder.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nick.reminder.R;

/**
 * Created by Nick on 1/10/2018.
 */

public class ExampleFragment extends Fragment {

    private final int LAYOUT = R.layout.fragment_example;
    private View view;

    public static ExampleFragment getInstance() {
        Bundle args = new Bundle();
        ExampleFragment exampleFragment = new ExampleFragment();
        exampleFragment.setArguments(args);
        return exampleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);


        return view;
    }
}
