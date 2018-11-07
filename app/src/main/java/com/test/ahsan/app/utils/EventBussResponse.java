package com.test.ahsan.app.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by ahsan on 24/09/2018.
 */

public class EventBussResponse {

    private Intent intent;
    private int resultCode;
    private Fragment fragment;

    public EventBussResponse(int resultCode, Intent intent, Bundle bundle, Fragment fragment) {
        this.intent = intent;
        this.resultCode = resultCode;
        this.fragment = fragment;
        if(fragment!=null){
            fragment.setArguments(bundle);
        }
    }

    public Fragment getFragment() {
        return fragment;
    }
    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
    public Intent getIntent() {
        return intent;
    }
    public int getResultCode() {
        return resultCode;
    }
}
