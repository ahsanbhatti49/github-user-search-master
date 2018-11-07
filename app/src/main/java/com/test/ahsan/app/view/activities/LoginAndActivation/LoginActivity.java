//package com.finja.abl.wallet.view.activities.LoginAndActivation;
//
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransaction;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//
//import com.finja.abl.wallet.R;
//import com.finja.abl.wallet.utils.AppConstants;
//import com.finja.abl.wallet.utils.NotificationUtil;
//import com.finja.abl.wallet.view.activities.BaseActivity;
//import com.finja.abl.wallet.view.fragments.LoginAndActivation.LoginInFragment;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class LoginActivity extends BaseActivity implements NotificationUtil.DecisionListener {
//
//    @BindView(R.id.ivAblLogo)
//    ImageView ivAblLogo;
//    @BindView(R.id.mainFrameLayout)
//    FrameLayout mainFrameLayout;
//
//    android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        ButterKnife.bind(this);
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction
//                .add(R.id.mainFrameLayout, new LoginInFragment())
//                .addToBackStack(null)
//                .setCustomAnimations(
//                        R.anim.enter_from_right,
//                        R.anim.exit_from_left,
//                        R.anim.pop_enter,
//                        R.anim.pop_exit
//                )
//                .commit();
//    }
//
//    @Override
//    public void onBackPressed() {
//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.mainFrameLayout);
//        if(fragment instanceof LoginInFragment){
//            NotificationUtil.getInstance().showDecisionAlert(this,"Alert","Do you want to exit?",this);
//            return;
//        }
//        else{
//            super.onBackPressed();
//        }
//    }
////    @Override
////    public void onStart() {
////        super.onStart();
////        EventBus.getDefault().register(this);
////    }
////
////    @Override
////    public void onStop() {
////        super.onStop();
////        EventBus.getDefault().unregister(this);
////    }
////
////    /*
////    *  Interation between fragments and main Main Activity
////    */
////
////    @Subscribe(threadMode = ThreadMode.MAIN)
////    public void onMessageEvent(EventBussResponse event) {
////        int fragmentId = event.getResultCode();
////        switch (fragmentId) {
////
////            case AppConstants.FRAGMENT_LOGIN_TRANSACTION:
////
////                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
////                fragmentTransaction
////                        .add(R.id.mainFrameLayout, event.getFragment())
////                        .addToBackStack(null)
////                        .setCustomAnimations(
////                                R.anim.enter_from_right,
////                                R.anim.exit_from_left,
////                                R.anim.pop_enter,
////                                R.anim.pop_exit
////                        )
////                        .commit();
////                break;
////
////
////        }
////    }
//
//    public void SelectFragment(int fragmentId) {
//        Fragment fragment = null;
//        switch (fragmentId) {
//            case AppConstants.FRAGMENT_LOGIN:
//                fragment = new LoginInFragment();
//                break;
//            case AppConstants.FRAGMENT_REGISTER:
//                fragment = new CreateUserDetails();
//                break;
//        }
//        if (fragment != null) {
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction
//                    .add(R.id.mainFrameLayout, fragment)
//                    .addToBackStack(null)
//                    .commit();
//        }
//    }
//
//    @Override
//    public void onDecide(boolean decide) {
//        if(decide){
//            this.finish();
//        }
//    }
//}
