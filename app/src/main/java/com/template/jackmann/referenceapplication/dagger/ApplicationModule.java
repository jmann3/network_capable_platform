package com.template.jackmann.referenceapplication.dagger;

import android.content.Context;
import android.content.res.Resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.jackmann.referenceapplication.application.ReferenceApp;
import com.template.jackmann.referenceapplication.http.AuthenticatedRequestErrorHandler;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jackmann on 7/18/16.
 */

@Module
public class ApplicationModule {

    private final ReferenceApp application;

    public ApplicationModule(ReferenceApp application) {
        this.application = application;
    }

    @Provides
    Context context() {
        return application;
    }

    @Provides
    Resources provideResources() {
        return application.getResources();
    }

    @Provides
    AuthenticatedRequestErrorHandler authenticatedRequestErrorHandler() {
        return new AuthenticatedRequestErrorHandler(application);
    }

    @Provides
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Provides
    @Named("IO_SCHEDULER")
    Scheduler provideIOScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Named("MAIN_SCHEDULER")
    Scheduler provideMainScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
