package com.template.jackmann.referenceapplication.rxJava;

import rx.Subscriber;
import timber.log.Timber;

/**
 * Created by williamvanderhoef on 5/16/16.
 */
public abstract class NetworkSubscriber<T> extends Subscriber<T> {

    public abstract void onSubscribedError (Throwable t);

    public abstract void onSubscribedCompleted ();

    @Override
    public final void onError (Throwable t) {
        Timber.w(t, "Unexpected error");
        if (!this.isUnsubscribed()) {
            onSubscribedError(t);
        }
    }

    @Override
    public final void onCompleted () {
        if (!this.isUnsubscribed()) {
            onSubscribedCompleted();
        }
    }
}
