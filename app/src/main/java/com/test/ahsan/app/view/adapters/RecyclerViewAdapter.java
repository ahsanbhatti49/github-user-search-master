package com.test.ahsan.app.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.ahsan.app.R;
import com.test.ahsan.app.model.dao.GithubFollowerModel;
import com.test.ahsan.app.utils.UrlImageView;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahsan on 07/11/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<GithubFollowerModel> githubFollowerModels;


    public RecyclerViewAdapter(List<GithubFollowerModel> githubFollowerModels) {
        this.githubFollowerModels = githubFollowerModels;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new RecyclerViewAdapter.RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_follower, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapter.RecyclerViewHolder holder, int position) {
        try {

            holder.tvName.setText(githubFollowerModels.get(position).getLogin());
            URI uri = new URI(githubFollowerModels.get(position).getAvatarUrl());
            URL url = uri.toURL();
            holder.imageView.setImageURL(url);
        } catch (Exception e) {
            Log.d("exception ", e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return this.githubFollowerModels.size();
    }


    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        UrlImageView imageView;
        TextView tvName;

        RecyclerViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.ivFollower);
            tvName = view.findViewById(R.id.tvName);
        }


    }
}