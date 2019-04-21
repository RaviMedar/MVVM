package com.ravilearning.mvvm.api;

import com.ravilearning.mvvm.api.models.Comment;
import com.ravilearning.mvvm.api.models.Post;

import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class DataProviderImpl implements DataProvider {

    private ApiService apiService;

    public DataProviderImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<List<Post>> getPosts() {
        return apiService.getPosts();
    }

    @Override
    public Observable<List<Comment>> getComments(final long postId) {
        return apiService.getComments().map(new Function<List<Comment>, List<Comment>>() {
            @Override
            public List<Comment> apply(List<Comment> comments) throws Exception {
                Iterator<Comment> iterator = comments.iterator();
                while (iterator.hasNext()) {
                    Comment c = iterator.next();
                    if (c.getId() != postId) {
                        iterator.remove();
                    }
                }

                return comments;
            }
        });
    }
}
