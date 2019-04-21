package com.ravilearning.mvvm.ui.posts;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.ravilearning.mvvm.ViewModel;
import com.ravilearning.mvvm.api.DataProvider;
import com.ravilearning.mvvm.api.models.Post;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PostsViewModel implements ViewModel {

    public final ObservableField<List<Post>> posts = new ObservableField<>();
    public final ObservableBoolean dataLoading = new ObservableBoolean(false);

    private DataProvider dataProvider;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public PostsViewModel(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public void loadPosts() {
        dataLoading.set(true);
        Disposable disposable = dataProvider.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Post>>() {
                    @Override
                    public void accept(List<Post> newPosts) throws Exception {
                        posts.set(newPosts);
                        dataLoading.set(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        // TODO: Implement error handling
                        dataLoading.set(false);
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        dataProvider = null;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
