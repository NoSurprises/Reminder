package nick.reminder.fragment;

import android.content.Context;
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

public abstract class BaseTabFragment extends Fragment {
    protected final int titleResource = R.string.tab_history;
    private final int LAYOUT = R.layout.fragment_example;
    protected View view;
    protected Context mainContext;
    private String title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        return view;
    }

    public abstract String getTitle();

    public void setMainContext(Context c) {
        mainContext = c;
    }
}
