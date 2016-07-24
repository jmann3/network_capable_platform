package com.template.jackmann.referenceapplication.http;

import models.Permit;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by jackmann on 7/20/16.
 */
public interface HttpApi {

    @GET("/resource/ydr8-5enu.json?")
    Observable<Permit[]> getPermitArray();
}
