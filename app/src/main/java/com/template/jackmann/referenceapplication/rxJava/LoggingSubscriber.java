package com.template.jackmann.referenceapplication.rxJava;

import javax.inject.Inject;

import rx.Subscriber;
import timber.log.Timber;

/**
 * @param <T>
 */
public class LoggingSubscriber<T> extends Subscriber<T> {

    @Inject
    public LoggingSubscriber () {
        // for dagger
    }

    @Override
    public void onError (Throwable t) {
        Timber.e(t, t.getMessage());
    }

    @Override
    public void onCompleted () {

    }

    @Override
    public void onNext (T t) {

    }
}