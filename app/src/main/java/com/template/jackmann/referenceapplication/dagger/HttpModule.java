package com.template.jackmann.referenceapplication.dagger;

import com.template.jackmann.referenceapplication.HttpApi;
import com.template.jackmann.referenceapplication.dagger.scope.ApplicationScope;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by jackmann on 7/18/16.
 */

@ApplicationScope
@Module
public class HttpModule {

    private static final String BASE_URL = "";

    @Provides
    public CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }

    @Provides
    public HttpLoggingInterceptor.Logger provideLogger() {
        return new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.d(message);
            }
        };
    }

    @Provides
    public HttpLoggingInterceptor provideLoggingInterceptor(HttpLoggingInterceptor.Logger logger) {
        return new HttpLoggingInterceptor(logger);
    }

    @Provides
    public Interceptor provideInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request.Builder requestBuilder = chain.request()
                        .newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json");

                return chain.proceed(requestBuilder.build());
            }
        };
    }

    @Provides
    public HttpApi provideHttpApi(HttpLoggingInterceptor loggingInterceptor, Interceptor headersInterceptor) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);

        // network interceptor provides headers for authenticated users
        builder.addNetworkInterceptor(headersInterceptor);

        return new Retrofit.Builder()
                // TODO: add this back when calls are from base url
                //.baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(builder.build())
                .build()
                .create(HttpApi.class);
    }
}
