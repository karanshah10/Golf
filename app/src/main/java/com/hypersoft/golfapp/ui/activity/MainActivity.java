package com.hypersoft.golfapp.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.hypersoft.golfapp.R;
import com.hypersoft.golfapp.databinding.ActivityMainBinding;
import com.hypersoft.golfapp.ui.communicator.FragmentReplaceCommunicator;
import com.hypersoft.golfapp.ui.fragment.GolfCourseListFragment;


public class MainActivity extends BaseActivity implements FragmentReplaceCommunicator {
    private ActivityMainBinding binding;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    GolfCourseListFragment golfCourseListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar.mainToolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.drawerlayout, binding.toolbar.mainToolbar, R.string.Drawer_Open, R.string.Drawer_close);
        binding.drawerlayout.addDrawerListener(actionBarDrawerToggle);
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainContent, new GolfCourseListFragment());
        fragmentTransaction.commit();

        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        binding.drawerlayout.closeDrawers();
                        item.setChecked(true);
                        break;
                    case R.id.menu_logout:
                        startActivity(new Intent(MainActivity.this, SignInActivity.class));
                        binding.drawerlayout.closeDrawers();
                        item.setChecked(true);
                }
                return false;
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void fragmentReplace(Fragment fragment) {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainContent, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}
