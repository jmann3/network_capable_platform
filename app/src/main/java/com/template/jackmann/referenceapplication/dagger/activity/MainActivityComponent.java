package com.template.jackmann.referenceapplication.dagger.activity;

import com.template.jackmann.referenceapplication.activity.MainActivity;
import com.template.jackmann.referenceapplication.dagger.ApplicationComponent;
import com.template.jackmann.referenceapplication.dagger.HttpModule;
import com.template.jackmann.referenceapplication.dagger.scope.ActivityScope;

import dagger.Component;

/**
 * Created by jackmann on 7/20/16.
 */

@ActivityScope
@Component(modules = {MainActivityModule.class, HttpModule.class},
                        dependencies = {ApplicationComponent.class})
public interface MainActivityComponent {

    void inject(MainActivity activity);
}
