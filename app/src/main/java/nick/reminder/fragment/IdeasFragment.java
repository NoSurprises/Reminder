package nick.reminder.fragment;


import nick.reminder.R;

/**
 * Created by Nick on 1/10/2018.
 */

public class IdeasFragment extends BaseTabFragment {
    protected final int titleResource = R.string.tab_ideas;

    @Override
    public String getTitle() {
        return mainContext.getString(titleResource);
    }
}

