package com.tencent.freestyle.ui.base;


import com.tencent.freestyle.api.ApiRetrofit;
import com.tencent.freestyle.api.ApiService;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Greyzhou on 2017/6/29.
 */

public class BasePresenter<V> {
    protected Reference<V> mViewRef;
    protected ApiService mApiService = ApiRetrofit.getInstance().getApiService();
    private CompositeSubscription mCompositeSubscription;
    public BasePresenter(V view){
        attachView(view);
    }


    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
        onUnsubscribe();
    }

    public V getView() {
        return mViewRef != null ? mViewRef.get() : null;
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
