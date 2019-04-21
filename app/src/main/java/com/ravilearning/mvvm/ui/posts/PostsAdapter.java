package com.ravilearning.mvvm.ui.posts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ravilearning.mvvm.api.models.Post;
import com.ravilearning.mvvm.databinding.ItemPostBinding;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Post> posts;

    public PostsAdapter() {
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPostBinding itemBinding = ItemPostBinding.inflate(layoutInflater, parent, false);
        return new PostViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PostViewHolder) holder).bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
       return posts == null ? 0 : posts.size();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }
}
