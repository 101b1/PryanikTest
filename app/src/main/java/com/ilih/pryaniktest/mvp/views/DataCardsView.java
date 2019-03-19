package com.ilih.pryaniktest.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


public interface DataCardsView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showEventSource(int pos, String name, boolean isCard);

    @StateStrategyType(SingleStateStrategy.class)
    void emptyCommand();

}
