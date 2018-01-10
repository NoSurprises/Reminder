package nick.reminder.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import nick.reminder.fragment.ExampleFragment;

/**
 * Created by Nick on 1/10/2018.
 */

public class TabsPagerFragmentAdapter extends FragmentPagerAdapter {

    private String[] tabs;

    public TabsPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[]{"tab1", "Notifications", "tab2"};
    }

    @Override
    public Fragment getItem(int position) {
        return ExampleFragment.getInstance();

//        switch (position) {
//            case 0:
//            case 1:
//                return ExampleFragment.getInstance();
//
//
//        }
//
//
//        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
