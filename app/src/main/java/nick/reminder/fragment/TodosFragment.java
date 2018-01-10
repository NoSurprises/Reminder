package nick.reminder.fragment;


import nick.reminder.R;

/**
 * Created by Nick on 1/10/2018.
 */

public class TodosFragment extends BaseTabFragment {
    protected final int titleResource = R.string.tab_todos;
    private final int fragmentLayout = R.layout.fragment_todos;


    @Override
    public int getTitleResource() {
        return titleResource;
    }

    @Override
    public int getFragmentLayout() {
        return fragmentLayout;
    }
}
