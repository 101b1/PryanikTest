package com.ilih.pryaniktest.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ServeRequestView extends MvpView {

    void showProgress();
    void hideProgress();
    void showRequestError();

    @StateStrategyType(SkipStrategy.class)
    void showRequestResult();
}
