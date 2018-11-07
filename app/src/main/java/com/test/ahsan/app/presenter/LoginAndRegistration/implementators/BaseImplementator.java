package com.test.ahsan.app.presenter.LoginAndRegistration.implementators;

import android.content.Context;

import com.test.ahsan.app.model.interacter.NetworkCommunicator.NetworkCommunicationInteractor;
import com.test.ahsan.app.model.interacter.NetworkCommunicator.NetworkResponseListener;
import com.test.ahsan.app.presenter.LoginAndRegistration.callbacks.BaseNetworkResponse;
import com.test.ahsan.app.view.viewContracter.loginAndRegistration.BaseNetworkContractor;

import org.json.JSONObject;

/**
 * Created by ahsan on 16/10/2018.
 */

public class BaseImplementator implements BaseNetworkResponse, NetworkResponseListener.onResponseListener {
    private BaseNetworkContractor baseNetworkContractor;
    private NetworkResponseListener networkResponseListener;

    public BaseImplementator(BaseNetworkContractor ckUserContractor) {
        this.baseNetworkContractor = ckUserContractor;
        networkResponseListener = new NetworkCommunicationInteractor();
    }

    @Override
    public void onError() {
    }

    @Override
    public void onSuccess(String responseModel) {
        baseNetworkContractor.hideProgress();
        baseNetworkContractor.displayResult(responseModel);
    }

    @Override
    public void onFailure(String message) {
        baseNetworkContractor.hideProgress();
        baseNetworkContractor.onError(message);

    }

    @Override
    public void getApiResponse(Context context,String username) {
        networkResponseListener.getResponse(context, username,this);
    }

}
