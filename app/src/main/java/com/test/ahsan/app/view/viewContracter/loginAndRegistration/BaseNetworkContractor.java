package com.test.ahsan.app.view.viewContracter.loginAndRegistration;

/**
 * Created by ahsan on 16/10/2018.
 */

public interface BaseNetworkContractor {
    void showProgress();
    void hideProgress();
    void onError(String errorMsg);
    void displayResult(String response);
}
