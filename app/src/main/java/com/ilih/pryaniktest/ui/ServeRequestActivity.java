package com.ilih.pryaniktest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.snackbar.Snackbar;
import com.ilih.pryaniktest.R;
import com.ilih.pryaniktest.mvp.presenters.ServeRequestPresenter;
import com.ilih.pryaniktest.mvp.views.ServeRequestView;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ServeRequestActivity extends MvpAppCompatActivity implements ServeRequestView {

    @InjectPresenter
    ServeRequestPresenter mPresenter;

    @BindView(R.id.text_status)
    TextView mTextStatus;
    @BindView(R.id.edit_address)
    EditText mAddressEdit;
    @BindView(R.id.button_send)
    Button mSendRequestButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        ButterKnife.bind(this);

        mSendRequestButton.setOnClickListener(v -> mPresenter.buttonClicked());
        mAddressEdit.setText("JSONSample.json");

    }

    @Override
    public void showProgress() {
        mTextStatus.setText(R.string.text_status_progress);
        mPresenter.sendRequest(mAddressEdit.getText().toString());
    }

    @Override
    public void hideProgress() {
        mTextStatus.setText(R.string.text_status_help);
    }

    @Override
    public void showRequestError() {
        Snackbar.make(mTextStatus, "Something is wrong! Check server address.", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showRequestResult() {
        Snackbar.make(mSendRequestButton, "RESULT OK", Snackbar.LENGTH_LONG).show();
        Intent intent = new Intent(this, DataCardsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
