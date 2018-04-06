package com.example.y_xl.wan.util;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/3/5.
 */

//因为只用一个方法所以用一个抽象方法来占位

public abstract class BaseObserver implements Observer<String> {
    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }
    //source=android
    @Override
    public void onNext(@NonNull String s) {
        onSuccess(s);
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
    public abstract void onSuccess(String result);
}
