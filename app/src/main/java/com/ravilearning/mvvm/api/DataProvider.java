package com.ravilearning.mvvm.api;

import com.ravilearning.mvvm.api.models.Comment;
import com.ravilearning.mvvm.api.models.Post;

import java.util.List;

import io.reactivex.Observable;

public interface DataProvider {

    Observable<List<Post>> getPosts();

    Observable<List<Comment>> getComments(long postId);
}
