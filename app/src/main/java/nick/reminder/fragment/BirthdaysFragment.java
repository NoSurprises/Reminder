package nick.reminder.fragment;


import nick.reminder.R;

/**
 * Created by Nick on 1/10/2018.
 */

public class BirthdaysFragment extends BaseTabFragment {
    protected final int titleResource = R.string.tab_birthdays;
    private final int fragmentLayout = R.layout.fragment_birthdays;


    @Override
    public int getTitleResource() {
        return titleResource;
    }

    @Override
    public int getFragmentLayout() {
        return fragmentLayout;
    }


}
