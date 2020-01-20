package com.hypersoft.golfapp.ui.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.hypersoft.golfapp.R;
import com.hypersoft.golfapp.databinding.ActivityMapBinding;
import com.hypersoft.golfapp.ui.communicator.FragmentReplaceCommunicator;
import com.hypersoft.golfapp.ui.fragment.MapsFragmentFragment;

/**
 * Created by Apple on 7/20/2017.
 */

public class MapActivity extends BaseActivity implements FragmentReplaceCommunicator {
    ActivityMapBinding binding;
    FragmentTransaction fragmentTransaction;
    int MapActivity;
    MapsFragmentFragment mapsFragmentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map);
        setSupportActionBar(binding.toolbar.mainToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Map");
        Intent intent = getIntent();
        MapActivity = getIntent().getIntExtra("MAPACTIVITY", 1);
        mapsFragmentFragment = new MapsFragmentFragment();
        fragmentTransaction = getFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("value", MapActivity);
        mapsFragmentFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.mainContent, mapsFragmentFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void fragmentReplace(Fragment fragment) {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainContent, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
