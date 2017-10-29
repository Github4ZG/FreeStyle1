package com.tencent.freestyle.api;

import com.tencent.freestyle.app.ApiConstant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Greyzhou on 2017/9/20.
 */

public class ApiRetrofit {
    private volatile static ApiRetrofit mInstance;
    private ApiService mApiService;
    private ApiRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mApiService= retrofit.create(ApiService.class);
    }

    public static ApiRetrofit getInstance(){
        if(mInstance == null){
            synchronized (ApiRetrofit.class){
                if(mInstance == null){
                    mInstance = new ApiRetrofit();
                }
            }
        }
        return mInstance;
    }

    public ApiService getApiService() {
        return mApiService;
    }
}
