package com.test.ahsan.app.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.ahsan.app.R;
import com.test.ahsan.app.model.dao.GithubFollowerModel;
import com.test.ahsan.app.presenter.LoginAndRegistration.implementators.BaseImplementator;
import com.test.ahsan.app.utils.NotificationUtil;
import com.test.ahsan.app.utils.UrlImageView;
import com.test.ahsan.app.view.adapters.RecyclerViewAdapter;
import com.test.ahsan.app.view.viewContracter.loginAndRegistration.BaseNetworkContractor;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ahsan on 07/11/2018.
 */

public class SearchDetail extends Fragment implements BaseNetworkContractor {


    private Bundle bundleIntent;
    private BaseImplementator baseImplementator;
    private RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bundleIntent = getArguments();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_detail, container, false);

        baseImplementator = new BaseImplementator(this);

        recyclerView = view.findViewById(R.id.tvFollowerListing);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        TextView textView1 = view.findViewById(R.id.tvUsername);
        TextView textView2 = view.findViewById(R.id.tvEmail);

        textView1.setText(bundleIntent.getString("username"));
        textView2.setText(bundleIntent.getString("email"));
        showProgress();
        try {
            URI uri = new URI(bundleIntent.getString("avatar"));
            URL url = uri.toURL();
            ((UrlImageView) view.findViewById(R.id.ivUserAvatar)).setImageURL(url);
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }


        baseImplementator.getApiResponse(getActivity(), "1234555555555555555" + bundleIntent.getString("followers"));


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
    public void displayResult(String response) {
        Gson gson = new Gson();
        if (response == null) {
            NotificationUtil.getInstance().showErrorAlert(getActivity(), "Error", "No Record found try again");
        } else {
            List<GithubFollowerModel> githubUserModel = gson.fromJson(response, new TypeToken<ArrayList<GithubFollowerModel>>() {
            }.getType());
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(githubUserModel);
            recyclerView.setAdapter(adapter);
            hideProgress();
            Log.d("sdfsdf", "done");

        }
    }
}

