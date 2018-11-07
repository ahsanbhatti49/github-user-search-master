package com.test.ahsan.app.model.interacter.NetworkCommunicator;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by ahsan on 11/09/2018.
 */

public interface NetworkResponseListener {
    interface onResponseListener {
        void onError();

        void onSuccess(String response);

        void onFailure(String message);
    }

    void getResponse(Context context,String username, onResponseListener onResponseListener);
}
