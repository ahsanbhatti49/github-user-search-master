package com.test.ahsan.app.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.google.gson.Gson;
import com.test.ahsan.app.R;
import com.test.ahsan.app.model.dao.GithubUserModel;
import com.test.ahsan.app.presenter.LoginAndRegistration.implementators.BaseImplementator;
import com.test.ahsan.app.utils.AppConstants;
import com.test.ahsan.app.utils.EmptyUtil;
import com.test.ahsan.app.utils.EventBussResponse;
import com.test.ahsan.app.utils.NotificationUtil;
import com.test.ahsan.app.view.viewContracter.loginAndRegistration.BaseNetworkContractor;
import org.greenrobot.eventbus.EventBus;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HomeFragment extends Fragment implements BaseNetworkContractor {
    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.btnGetRecord)
    Button btnGetRecord;
    Unbinder unbinder;

    private BaseImplementator baseImplementator;

    private HomeFragment.OnFragmentInteractionListener mListener;


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Bundle bundle);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
           // mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeFragment.OnFragmentInteractionListener) {
            mListener = (HomeFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        unbinder = ButterKnife.bind(this, view);

        setupPresenters();


        return view;
    }


    private void setupPresenters() {

        baseImplementator = new BaseImplementator(this);

    }


    @Override
    public void showProgress() {
        NotificationUtil.getInstance().showLoading(getActivity());

    }

    @Override
    public void hideProgress() {
        NotificationUtil.getInstance().dismissLoading();
    }

    @Override
    public void onError(String errorMsg) {
        NotificationUtil.getInstance().showErrorAlert(getActivity(), "Error", errorMsg);
    }

    @Override
    public void displayResult(String responseModel) {
        Gson gson = new Gson();
        if (responseModel == null) {
            NotificationUtil.getInstance().showErrorAlert(getActivity(), "Error", "No Record found try again");
        } else {
            GithubUserModel githubUserModel = gson.fromJson(responseModel, GithubUserModel.class);

            Bundle bundle = new Bundle();
            bundle.putString("username", githubUserModel.getName());
            bundle.putString("email", githubUserModel.getEmail());
            bundle.putString("avatar", githubUserModel.getAvatarUrl());
            bundle.putString("followers", githubUserModel.getFollowersUrl());
            mListener.onFragmentInteraction(bundle);
//            EventBus.getDefault()
//
//                    .post(new EventBussResponse(AppConstants.GOTO_FRAGMENT,
//                            new Intent().putExtra("tag", getString(R.string.SearchDetail)), bundle,
//                            new SearchDetail()
//                    ));

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (baseImplementator != null) {
            baseImplementator = null;
        }
    }

    @OnClick(R.id.btnGetRecord)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnGetRecord:
                if (EmptyUtil.isValid(etUsername)) {
                    showProgress();
                    baseImplementator.getApiResponse(this.getContext(), etUsername.getText().toString());
                }
                break;
        }
    }

}
