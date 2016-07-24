package com.template.jackmann.referenceapplication.dagger;

import android.content.res.Resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.jackmann.referenceapplication.application.ReferenceApp;

import com.template.jackmann.referenceapplication.dagger.scope.ApplicationScope;
import com.template.jackmann.referenceapplication.http.AuthenticatedRequestErrorHandler;

import javax.inject.Named;

import dagger.Component;
import rx.Scheduler;

/**
 * Created by jackmann on 7/18/16.
 */

@ApplicationScope
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject (ReferenceApp application);

    // Exposes injected types to modules listing ApplicationComponent as a dependency
    Resources resources ();

    ObjectMapper objectMapper ();

    @Named("IO_SCHEDULER")
    Scheduler ioScheduler();

    @Named("MAIN_SCHEDULER")
    Scheduler mainScheduler();

    AuthenticatedRequestErrorHandler authenticatedRequestErrorHandler();
}
