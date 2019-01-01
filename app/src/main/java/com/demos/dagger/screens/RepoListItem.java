package com.demos.dagger.screens;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.demos.dagger.R;
import com.demos.dagger.models.GitHubRepos;
import com.squareup.picasso.Picasso;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoListItem extends FrameLayout {

    private Picasso picasso;

    @BindView(R.id.user_avatar_image)
    ImageView avatar;

    @BindView(R.id.repo_name)
    TextView name;

    @BindView(R.id.repo_description)
    TextView description;

    @BindView(R.id.repo_stars)
    TextView stars;

    @BindView(R.id.repo_issues)
    TextView issues;

    @BindView(R.id.repo_forks)
    TextView forks;

    @BindView(R.id.repo_updated_at)
    TextView updatedAt;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.fullDate();

    public RepoListItem(Context context, Picasso picasso) {
        super(context);
        this.picasso = picasso;
        init();
    }

    public RepoListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RepoListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.repo_list_item, this);
        ButterKnife.bind(this);
    }

    public void setRepo(GitHubRepos githubRepo) {
        Locale locale = getResources().getConfiguration().locale;

        name.setText(githubRepo.name);
        description.setText(githubRepo.description);

        stars.setText(String.format(locale, "%d", githubRepo.stargazersCount));
        issues.setText(String.format(locale, "%d", githubRepo.openIssuesCount));
        forks.setText(String.format(locale, "%d", githubRepo.forksCount));

        updatedAt.setText(githubRepo.updatedAt);

        picasso.cancelRequest(avatar);

        picasso.load(githubRepo.owner.avatarUrl)
                .fit()
                .into(avatar);
    }
}
