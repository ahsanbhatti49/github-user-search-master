package com.test.ahsan.app.view.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.test.ahsan.app.R;
import com.test.ahsan.app.view.fragments.HomeFragment;
import com.test.ahsan.app.view.fragments.SearchDetail;

import butterknife.ButterKnife;

/**
 * Created by ahsan on 06/11/2018.
 */

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        ButterKnife.bind(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new HomeFragment();
        fragmentTransaction
                .add(R.id.mainFrameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onFragmentInteraction(Bundle bundle) {
        SearchDetail frag = new SearchDetail();
        frag.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction
                .add(R.id.mainFrameLayout, frag)
                .addToBackStack(null)
                .commit();
    }
}
