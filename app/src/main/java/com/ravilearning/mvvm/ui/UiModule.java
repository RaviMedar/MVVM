package com.ravilearning.mvvm.ui;

import com.ravilearning.mvvm.api.DataProvider;
import com.ravilearning.mvvm.ui.postDetails.PostDetailsViewModel;
import com.ravilearning.mvvm.ui.posts.PostsViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class UiModule {

    @Provides
    PostsViewModel providesPostsViewModel(DataProvider dataProvider) {
        return new PostsViewModel(dataProvider);
    }

    @Provides
    PostDetailsViewModel providesPostsDetailsViewModel(DataProvider dataProvider) {
        return new PostDetailsViewModel(dataProvider);
    }

}
