package nick.reminder.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;

import nick.reminder.fragment.BaseTabFragment;
import nick.reminder.fragment.BirthdaysFragment;
import nick.reminder.fragment.HistoryFragment;
import nick.reminder.fragment.IdeasFragment;
import nick.reminder.fragment.TodosFragment;

/**
 * Created by Nick on 1/10/2018.
 */

public class TabsPagerFragmentAdapter extends FragmentPagerAdapter {

    private final Context context;
    private Map<Integer, BaseTabFragment> tabs;

    public TabsPagerFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;

        tabs = new HashMap<>();

        BaseTabFragment[] appFragments = {new HistoryFragment(), new IdeasFragment(),
                new TodosFragment(), new BirthdaysFragment()};
        for (int i = 0; i < appFragments.length; i++) {
            appFragments[i].setMainContext(context);
            tabs.put(i, appFragments[i]);
        }

    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }
}
