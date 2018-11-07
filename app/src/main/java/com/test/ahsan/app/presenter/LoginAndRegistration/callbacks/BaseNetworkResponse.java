package com.test.ahsan.app.presenter.LoginAndRegistration.callbacks;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by ahsan on 16/10/2018.
 */

public interface BaseNetworkResponse {
    void getApiResponse(Context context,String username);
}
