package com.demos.dagger.screens;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.demos.dagger.models.GitHubRepos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdapterRepos extends BaseAdapter {
    private final List<GitHubRepos> repoList = new ArrayList<>(0);
    private final Context context;
    private Picasso picasso;

    public AdapterRepos(Context context, Picasso picasso) {
        this.context = context;
        this.picasso = picasso;
    }

    @Override
    public int getCount() {
        return repoList.size();
    }

    @Override
    public GitHubRepos getItem(int position) {
        return repoList.get(position);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public long getItemId(int position) {
        return repoList.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RepoListItem repoListItem;
        if(convertView == null) {
            repoListItem = new RepoListItem(context,picasso);
        } else {
            repoListItem = RepoListItem.class.cast(convertView);
        }

        repoListItem.setRepo(repoList.get(position));

        return repoListItem;
    }

    public void swapData(Collection<GitHubRepos> githubRepos) {
        repoList.clear();
        if(githubRepos != null) {
            repoList.addAll(githubRepos);
        }
        notifyDataSetChanged();
    }

}
