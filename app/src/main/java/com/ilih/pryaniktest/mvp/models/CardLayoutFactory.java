package com.ilih.pryaniktest.mvp.models;

import com.ilih.pryaniktest.R;

public class CardLayoutFactory {

    public static int getCardLayout(CardType type) {
        switch (type) {
            case hz:
                return R.layout.card_text;
            case picture:
                return R.layout.card_pictures;
            case selector:
                return R.layout.card_spinner;
            default:
                return R.layout.card_text;
        }
    }

}
