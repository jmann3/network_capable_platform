package com.template.jackmann.referenceapplication.dagger.activity;

import android.support.annotation.NonNull;

import com.template.jackmann.referenceapplication.activity.MainActivity;
import com.template.jackmann.referenceapplication.http.AuthenticatedRequestErrorHandler;
import com.template.jackmann.referenceapplication.rxJava.ApiTransformer;

import dagger.Module;
import dagger.Provides;
import models.Permit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jackmann on 7/20/16.
 */

@Module
public class MainActivityModule {

    @NonNull
    private final MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    ApiTransformer<Permit[]> provideApiTransformer(AuthenticatedRequestErrorHandler errorHandler) {
        return new ApiTransformer<>(errorHandler, Schedulers.io(), AndroidSchedulers.mainThread());
    }
}
