package com.ilih.pryaniktest.app;

import android.app.Application;

import com.ilih.pryaniktest.mvp.models.gson.ServerData;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerApp extends Application {

    private Retrofit retrofit;
    private static ServerApi api;
    private static ServerData responseData;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://prnk.blob.core.windows.net/tmp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ServerApi.class);
    }

    public static ServerApi getApi(){
        return api;
    }

    public static void setResponseData(ServerData data){
        responseData = data;
    }

    public static ServerData getResponseData(){
        return responseData;
    }
}
