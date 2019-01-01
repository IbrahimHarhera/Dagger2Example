package com.demos.dagger.network;

import com.demos.dagger.models.GitHubRepos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("users/{username}/repos")
    Call<List<GitHubRepos>> getReposForUser(@Path("username") String username);

    @GET("repositories")
    Call<List<GitHubRepos>> getAllRepos();

    @GET("users/{username}")
    Call<GitHubRepos> getUser(@Path("username") String userName);
}
