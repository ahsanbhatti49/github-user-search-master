//package com.finja.abl.wallet.presenter.implementators;
//
//import android.content.Context;
//
//import com.finja.abl.wallet.model.interacter.NetworkCommunicator.NetworkResponseListener;
//import com.finja.abl.wallet.presenter.MainActivityPresenter;
//import com.finja.abl.wallet.view.viewContracter.MainFragmentContracter;
//
///**
// * Created by ahsan on 11/09/2018.
// */
//
//public class MainActivityPresenterImpl implements MainActivityPresenter,NetworkResponseListener.onResponseListener {
//
//    private MainFragmentContracter mainActivityContracter;
//    private NetworkResponseListener mainActivityInteracter;
//
//    public MainActivityPresenterImpl(MainFragmentContracter mainActivityContracter){
//        this.mainActivityContracter = mainActivityContracter;
//        mainActivityInteracter = new MainAcitivityInteracterImpl();
//    }
//
//
//    @Override
//    public void getServerResponse(Context context) {
//        mainActivityContracter.showProgress();
//        //mainActivityInteracter.getResponse(context,context.getString(R.string.stringGetMovies),this,true);
//    }
//    @Override
//    public void onError() {
//
//    }
//
//    @Override
//    public void onSuccess(String response) {
//        mainActivityContracter.hideProgress();
//        mainActivityContracter.displayResult(response);
//    }
//
//    @Override
//    public void onFailure(String message) {
//        mainActivityContracter.hideProgress();
////        mainActivityContracter.displayResult(message);
//
//    }
//
//}
