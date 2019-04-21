package com.ravilearning.mvvm.api;

import com.ravilearning.mvvm.api.models.Comment;
import com.ravilearning.mvvm.api.models.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

	@GET("posts")
	Observable<List<Post>> getPosts();

	@GET("comments")
	Observable<List<Comment>> getComments();
}