package com.test.ahsan.app.utils.NetworkUtil;

/**
 * Created by ahsan on 11/09/2018.
 */

public interface NetworkResponseListener {
    interface onResponseListener {
        void onError();

        void onSuccess(String response);

        void onFailure(String message);
    }

    void getResponse(onResponseListener onResponseListener);
}
