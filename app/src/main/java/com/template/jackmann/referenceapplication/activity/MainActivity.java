package com.template.jackmann.referenceapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.template.jackmann.referenceapplication.R;
import com.template.jackmann.referenceapplication.application.ReferenceApp;
import com.template.jackmann.referenceapplication.dagger.HttpModule;
import com.template.jackmann.referenceapplication.dagger.activity.DaggerMainActivityComponent;
import com.template.jackmann.referenceapplication.dagger.activity.MainActivityModule;
import com.template.jackmann.referenceapplication.http.HttpApi;
import com.template.jackmann.referenceapplication.rxJava.ApiTransformer;
import com.template.jackmann.referenceapplication.rxJava.NetworkSubscriber;

import javax.inject.Inject;

import models.Permit;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    @Inject
    HttpApi api;
    @Inject
    CompositeSubscription compositeSubscription;
    @Inject
    ApiTransformer<Permit[]> apiTransformer;

    @Nullable
    Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Welcome Again", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initializeDaggerComponents();

        performPermitRequest();
    }

    private void initializeDaggerComponents() {
        DaggerMainActivityComponent.builder()
                .applicationComponent(((ReferenceApp)getApplication()).getApplicationComponent())
                .httpModule(new HttpModule())
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();

        compositeSubscription.unsubscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeSubscription.unsubscribe();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void performPermitRequest() {
        subscription = api.getPermitArray()
                .compose(apiTransformer)
                .subscribe(new NetworkSubscriber<Permit[]>() {
                    @Override
                    public void onSubscribedError(Throwable t) {
                        Log.d("RxJava", "onError");
                    }

                    @Override
                    public void onSubscribedCompleted() {

                    }

                    @Override
                    public void onNext(Permit[] permits) {
                        Log.d("RxJava", "OnNext");
                    }
                });

        compositeSubscription.add(subscription);
    }
}
