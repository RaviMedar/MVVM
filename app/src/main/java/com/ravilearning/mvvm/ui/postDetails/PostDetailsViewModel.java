package com.ravilearning.mvvm.ui.postDetails;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.ravilearning.mvvm.ViewModel;
import com.ravilearning.mvvm.api.DataProvider;
import com.ravilearning.mvvm.api.models.Comment;
import com.ravilearning.mvvm.api.models.Post;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PostDetailsViewModel implements ViewModel {

    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> body = new ObservableField<>();
    public final ObservableField<String> comments = new ObservableField<>();

    public final ObservableBoolean dataLoading = new ObservableBoolean(false);

    private DataProvider dataProvider;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public PostDetailsViewModel(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public void setPost(Post post) {
        title.set(post.getTitle());
        body.set(post.getBody());
        Disposable disposable = dataProvider.getComments(post.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Comment>>() {
                    @Override
                    public void accept(List<Comment> data) throws Exception {
                        comments.set(String.valueOf(data.size()));
                        dataLoading.set(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
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
