package com.template.jackmann.referenceapplication.http;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import retrofit2.adapter.rxjava.HttpException;
import rx.functions.Action1;
import timber.log.Timber;

/**
 * Created by jackmann on 7/18/16.
 */
public class AuthenticatedRequestErrorHandler implements Action1<Throwable> {

    private final Context context;

    public AuthenticatedRequestErrorHandler(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public void call(Throwable throwable) {
        Timber.e(throwable, "Request Failed");
    }
}
