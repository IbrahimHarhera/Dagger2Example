package com.demos.dagger;

import android.app.Activity;
import android.app.Application;

import com.demos.dagger.network.GitHubService;
import com.fatboyindustrial.gsonjodatime.DateTimeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class App extends Application {
    private GitHubService githubService;
    private Picasso picasso;
    public static App get(Activity activity) {
        return (App) activity.getApplication();
    }


    // activity

    // githubService   Picasso

    // retrofit      OkHttp3Downloader


    // okHttp     gson


    //logger    cache    interceptor


    // file    timber

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        Gson gson = gsonBuilder.create();

        picasso=new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();

        Retrofit gitHubRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("https://api.github.com/")
                .build();

        githubService = gitHubRetrofit.create(GitHubService.class);
    }

    public GitHubService getGithubService() {
        return githubService;
    }

    public Picasso getPicasso() {
        return picasso;
    }
}

