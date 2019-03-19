package com.ilih.pryaniktest.app;

import com.ilih.pryaniktest.mvp.models.gson.ServerData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServerApi {

    @GET("{url}")
    Call<ServerData> getResponse(@Path("url") String url);
}
