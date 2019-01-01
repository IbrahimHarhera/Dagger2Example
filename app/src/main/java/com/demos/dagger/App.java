package com.demos.dagger;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.demos.dagger.dagger.ContextModule;
import com.demos.dagger.dagger.DaggerMyComponent;
import com.demos.dagger.dagger.GitHubServiceModule;
import com.demos.dagger.dagger.MyComponent;
import com.demos.dagger.dagger.NetworkModule;
import com.demos.dagger.dagger.PicassoModule;
import com.demos.dagger.network.GitHubService;
import com.fatboyindustrial.gsonjodatime.DateTimeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class App extends Application {
    private GitHubService githubService;
    private Picasso picasso;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        MyComponent component = DaggerMyComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        githubService = component.getGitHubService();
        picasso = component.getPicassp();
    }

    public GitHubService getGithubService() {
        return githubService;
    }

    public Picasso getPicasso() {
        return picasso;
    }

    public static App get(Activity activity) {
        return (App) activity.getApplication();
    }
}

