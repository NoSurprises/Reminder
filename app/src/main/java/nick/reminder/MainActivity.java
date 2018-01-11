package nick.reminder;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nick.reminder.adapter.TabsPagerFragmentAdapter;
import nick.reminder.database.ReminderDataModel;
import nick.reminder.database.ReminderHelper;
import nick.reminder.dto.RemindDTO;
import nick.reminder.fragment.BaseTabFragment;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "daywint";
    @BindView(R.id.toolbar) Toolbar toolBar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager pager;

    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase db;
    private TabsPagerFragmentAdapter pagerAdapter;

    public SQLiteDatabase getDb() {
        return db;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initToolbar();
        initNavigation();
        initViewPager();
        initDatabase();
        // TODO: 1/11/2018 get data from db


    }

    private void initDatabase() {
        dbHelper = new ReminderHelper(this);
        db = dbHelper.getWritableDatabase();
    }

    private void initNavigation() {
        setUpDrawerToggle();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        setDrawerMenuItemClickedListener(navigationView);
    }

    private void setDrawerMenuItemClickedListener(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawer.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nav_notification:
                        showNotificationTab();
                        break;
                }
                return true;
            }
        });
    }

    private void setUpDrawerToggle() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolBar, R.string.view_navigation_open,
                R.string.view_navigation_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initViewPager() {
        pagerAdapter = new TabsPagerFragmentAdapter(this, getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(pager);
    }

    private void initToolbar() {
        toolBar.setTitle(R.string.app_name);
        toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        toolBar.inflateMenu(R.menu.menu);
    }

    private void showNotificationTab() {
        pager.setCurrentItem(Constants.TAB_NOTIFICATIONS);
    }

    @OnClick(R.id.floating_button)
    void addNewRemind() {
        BaseTabFragment currentFragment = (BaseTabFragment) pagerAdapter.getItem(pager.getCurrentItem());
        RemindDTO newRemind = new RemindDTO("hello world");
        newRemind.setTab(currentFragment.getTitle());
        insertNewRemindToDatabase(newRemind);
        currentFragment.swapCursor(db);

    }

    private void insertNewRemindToDatabase(RemindDTO newRemind) {
        ContentValues cv = new ContentValues();
        cv.put(ReminderDataModel.TAB, newRemind.getTab());
        cv.put(ReminderDataModel.TITLE, newRemind.getTitle());
        cv.put(ReminderDataModel.DESCRIPTION, newRemind.getDescription());
        db.insert(ReminderHelper.DATABASE_NAME, null, cv);
        Log.d(TAG, "insertNewRemindToDatabase: inserted elem");
    }

}
