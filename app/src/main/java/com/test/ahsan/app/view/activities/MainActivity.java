package com.test.ahsan.app.view.activities;

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

//    /*
//   *  Interation between fragments and Main Activity
//   */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(EventBussResponse event) {
//        int fragmentId = event.getResultCode();
//        switch (fragmentId) {
//            case AppConstants.GOTO_FRAGMENT:
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction
//                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_left, R.anim.pop_enter, R.anim.pop_exit)
//                        .add(R.id.mainFrameLayout, event.getFragment(), event.getIntent().getStringExtra("tag"))
//                        .addToBackStack(null)
//                        .commit();
//                break;
////            case AppConstants.GOTO_FRAGMENT:
////                try {
////                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.mainFrameLayout);
////                    int fragmentCount = getSupportFragmentManager().getBackStackEntryCount();
////                    if (fragment instanceof HomeFragment || fragmentCount == 0) {
////                        return;
////                    } else {
////                        int loopThrough = fragmentCount - 1;
////                        for (int i = 0; i <= loopThrough; i++) {
////                            getSupportFragmentManager().popBackStackImmediate();
////                        }
////                    }
////                } catch (Exception e) {
////                }
////                break;
//        }
//    }


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
