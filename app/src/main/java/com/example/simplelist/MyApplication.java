package com.example.simplelist;

import android.app.Application;

import com.example.simplelist.presenters.MainPresenter;
import com.example.simplelist.services.SimpleService;

import org.androidannotations.annotations.EApplication;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andrei on 06.01.2018.
 */

@EApplication
public class MyApplication extends Application {

    private static SimpleService service;
    private static MainPresenter presenter;

    public void onCreate() {
        super.onCreate();

        init();
    }

    public void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://example.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        service = retrofit.create(SimpleService.class);
        presenter = new MainPresenter();
    }

    public static SimpleService getRetrofitService() {
        return service;
    }

    public static MainPresenter getPresenter() {
        return presenter;
    }
}
