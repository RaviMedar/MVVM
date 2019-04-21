package com.ravilearning.mvvm.ui.postDetails;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ravilearning.mvvm.MainApplication;
import com.ravilearning.mvvm.R;
import com.ravilearning.mvvm.api.models.Post;
import com.ravilearning.mvvm.databinding.ActivityPostDetailsBinding;

import javax.inject.Inject;

public class PostDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_POST = "EXTRA_POST";

    ActivityPostDetailsBinding dataBinding;

    @Inject
    PostDetailsViewModel postsViewModel;

    public static Intent create(Context context, Post post) {
        Intent intent = new Intent(context, PostDetailsActivity.class);
        intent.putExtra(EXTRA_POST, post);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        MainApplication.getInstance().getAppComponent().inject(this);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_post_details);
        dataBinding.setViewModel(postsViewModel);
        postsViewModel.setPost(getArgPost());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        postsViewModel.onDestroy();
    }

    private Post getArgPost() {
        return (Post) getIntent().getSerializableExtra(EXTRA_POST);
    }
}
