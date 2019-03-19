package com.ilih.pryaniktest.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.ilih.pryaniktest.app.ServerApp;
import com.ilih.pryaniktest.mvp.models.gson.ServerData;
import com.ilih.pryaniktest.mvp.views.ServeRequestView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class ServeRequestPresenter extends MvpPresenter<ServeRequestView> {

    public ServeRequestPresenter() {
        super();
    }

    public void buttonClicked() {

        getViewState().showProgress();
    }

    public void sendRequest(String url) {

        ServerApp.getApi().getResponse(url).enqueue(new Callback<ServerData>() {
            @Override
            public void onResponse(Call<ServerData> call, Response<ServerData> response) {

                if (response.body() == null) onFailure(call, null);
                else {
                    ServerApp.setResponseData(response.body());
                    getViewState().showRequestResult();
                    getViewState().hideProgress();
                }
            }

            @Override
            public void onFailure(Call<ServerData> call, Throwable t) {
                getViewState().showRequestError();
                getViewState().hideProgress();
            }
        });
    }
}
