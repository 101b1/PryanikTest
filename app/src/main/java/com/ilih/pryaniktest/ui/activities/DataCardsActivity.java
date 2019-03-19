package com.ilih.pryaniktest.ui.activities;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.snackbar.Snackbar;
import com.ilih.pryaniktest.R;
import com.ilih.pryaniktest.mvp.presenters.DataCardsPresenter;
import com.ilih.pryaniktest.mvp.views.DataCardsView;
import com.ilih.pryaniktest.ui.adapters.DataCardAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DataCardsActivity extends MvpAppCompatActivity implements DataCardsView, DataCardAdapter.CardClickListener {

    @InjectPresenter
    DataCardsPresenter mDataCardPresenter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cardlist);

        ButterKnife.bind(this);

        DataCardAdapter adapter = new DataCardAdapter(mDataCardPresenter.provideContent(), this);

        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }


    @Override
    public void showEventSource(int pos, String name, boolean isCard) {
        StringBuilder builder = new StringBuilder();
        builder.append("Click event provided by ");
        if(isCard){
            builder.append("card at position ");
        }else {
            builder.append("spinner item at position ");
        }
        builder.append(pos)
                .append(" and name ")
                .append(name);
        Snackbar.make(recyclerView, builder, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void emptyCommand(){

    }

    @Override
    public void onClicked(int position, String name, boolean isCard) {
        mDataCardPresenter.cardClickedAction(position, name, isCard);
    }
}
