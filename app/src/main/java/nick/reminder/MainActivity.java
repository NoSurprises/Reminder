package nick.reminder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import nick.reminder.adapter.TabsPagerFragmentAdapter;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar) Toolbar toolBar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager pager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initToolbar();
        initNavigation();
        initViewPager();

    }

    private void initNavigation() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolBar, R.string.view_navigation_open,
                R.string.view_navigation_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);

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

    private void initViewPager() {
        TabsPagerFragmentAdapter adapter = new TabsPagerFragmentAdapter(this, getSupportFragmentManager());
        pager.setAdapter(adapter);
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
}
