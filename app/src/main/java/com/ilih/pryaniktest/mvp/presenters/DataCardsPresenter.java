package com.ilih.pryaniktest.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.ilih.pryaniktest.app.ServerApp;
import com.ilih.pryaniktest.mvp.models.gson.ServerData;
import com.ilih.pryaniktest.mvp.views.DataCardsView;

@InjectViewState
public class DataCardsPresenter extends MvpPresenter<DataCardsView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().emptyCommand();
    }

    public ServerData provideContent() {
        return ServerApp.getResponseData();
    }

    public void cardClickedAction(int position, String name, boolean isCard) {
        getViewState().showEventSource(position, name, isCard);
    }

}
