package com.template.jackmann.referenceapplication.rxJava;

import android.support.annotation.NonNull;


import com.template.jackmann.referenceapplication.http.AuthenticatedRequestErrorHandler;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * @param <T>
 */
public class ApiTransformer<T> implements Observable.Transformer<T, T> {

    private final Scheduler subscribeOnScheduler;
    private final Scheduler observeOnScheduler;
    protected final AuthenticatedRequestErrorHandler errorHandler;

    @Inject
    public ApiTransformer(@NonNull AuthenticatedRequestErrorHandler errorHandler,
                          @NonNull @Named("IO_SCHEDULER") Scheduler subscribeOnScheduler,
                          @NonNull @Named("MAIN_SCHEDULER") Scheduler observeOnScheduler) {

        this.errorHandler = errorHandler;
        this.observeOnScheduler = observeOnScheduler;
        this.subscribeOnScheduler = subscribeOnScheduler;
    }

    @Override
    public Observable<T> call(Observable<T> observable) {
        return observable.subscribeOn(subscribeOnScheduler).observeOn(observeOnScheduler).doOnError(errorHandler);
    }
}