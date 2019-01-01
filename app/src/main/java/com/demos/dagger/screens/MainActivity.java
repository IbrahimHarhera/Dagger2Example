package com.demos.dagger.screens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.demos.dagger.App;
import com.demos.dagger.R;
import com.demos.dagger.models.GitHubRepos;
import com.demos.dagger.network.GitHubService;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    GitHubService githubService;

    Call<List<GitHubRepos>> reposCall;

    AdapterRepos adapterRepos;
    Picasso picasso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        githubService = App.get(this).getGithubService();
        picasso = App.get(this).getPicasso();

        ListView listView = findViewById(R.id.repo_home_list);

        adapterRepos = new AdapterRepos(this, picasso);
        listView.setAdapter(adapterRepos);


        reposCall = githubService.getReposForUser("patrick-doyle");
        reposCall.enqueue(new Callback<List<GitHubRepos>>() {
            @Override
            public void onResponse(Call<List<GitHubRepos>> call, Response<List<GitHubRepos>> response) {
                adapterRepos.swapData(response.body());
//                Log.e("URL", call.request().url().toString());
//                try {
//                    Log.e("URL", response.errorBody().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onFailure(Call<List<GitHubRepos>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error getting repos " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("URL", call.request().url().toString());
                Log.e("URL", t.getMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (reposCall != null) {
            reposCall.cancel();
        }
    }
}
