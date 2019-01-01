package com.demos.dagger.dagger;

import com.demos.dagger.network.GitHubService;
import com.squareup.picasso.Picasso;

import dagger.Component;

@Component(modules = {GitHubServiceModule.class, PicassoModule.class})
public interface MyComponent {

    Picasso getPicassp();

    GitHubService getGitHubService();
}
