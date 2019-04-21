package com.ravilearning.mvvm;

import com.ravilearning.mvvm.api.ApiModule;
import com.ravilearning.mvvm.ui.UiModule;
import com.ravilearning.mvvm.ui.postDetails.PostDetailsActivity;
import com.ravilearning.mvvm.ui.posts.PostsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, UiModule.class})
public interface AppComponent {

    void inject(PostsActivity postsActivity);

    void inject(PostDetailsActivity postDetailsActivity);

}
