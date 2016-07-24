package com.template.jackmann.referenceapplication.application;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.template.jackmann.referenceapplication.dagger.ApplicationComponent;
import com.template.jackmann.referenceapplication.dagger.ApplicationModule;
import com.template.jackmann.referenceapplication.dagger.DaggerApplicationComponent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by jackmann on 7/18/16.
 */
public class ReferenceApp extends Application{

    @Nullable
    ApplicationComponent applicationComponent;

    public static EventBus getEventBus () {
        return EventBus.getDefault();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = setupComponents();
        applicationComponent.inject(this);
    }

    @NonNull
    ApplicationComponent setupComponents () {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    @NonNull
    public ApplicationComponent getApplicationComponent () {
        if (applicationComponent == null) {
            applicationComponent = setupComponents();
        }

        return applicationComponent;
    }
}
