package com.ravilearning.mvvm.ui.posts;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.ravilearning.mvvm.MainApplication;
import com.ravilearning.mvvm.R;
import com.ravilearning.mvvm.api.models.Post;
import com.ravilearning.mvvm.databinding.ActivityPostsBinding;

import java.util.List;

import javax.inject.Inject;

public class PostsActivity extends AppCompatActivity {

    ActivityPostsBinding dataBinding;

    @Inject
    PostsViewModel postsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        MainApplication.getInstance().getAppComponent().inject(this);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_posts);
        dataBinding.setViewModel(postsViewModel);
        postsViewModel.loadPosts();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        dataBinding.list.setLayoutManager(llm);
        dataBinding.list.setAdapter(new PostsAdapter());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        postsViewModel.onDestroy();
    }

    @BindingAdapter("app:items")
    public static void setItems(RecyclerView listView, List<Post> items) {
        PostsAdapter adapter = (PostsAdapter) listView.getAdapter();
        if (adapter != null) {
            adapter.setPosts(items);
        }
    }
}
