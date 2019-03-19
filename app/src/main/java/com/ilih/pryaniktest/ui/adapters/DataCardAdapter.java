package com.ilih.pryaniktest.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ilih.pryaniktest.R;
import com.ilih.pryaniktest.mvp.models.CardLayoutFactory;
import com.ilih.pryaniktest.mvp.models.CardType;
import com.ilih.pryaniktest.mvp.models.gson.Data;
import com.ilih.pryaniktest.mvp.models.gson.Datum;
import com.ilih.pryaniktest.mvp.models.gson.ServerData;
import com.squareup.picasso.Picasso;


import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DataCardAdapter extends RecyclerView.Adapter<DataCardAdapter.ViewHolder> {

    List<Datum> mData;
    List<String> mViewTypes;
    Map<String, Integer> mDataMap;

    CardClickListener mListener;

//    @InjectPresenter
//    DataCardsPresenter mPresenter;

    public DataCardAdapter(ServerData data, CardClickListener listener){
        this.mData = data.getData();
        mViewTypes = data.getView();
        mDataMap = data.getDataMap();
        mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewTypePosition) {
        CardView cv = (CardView)LayoutInflater.from(parent.getContext())
                .inflate(CardLayoutFactory.getCardLayout(CardType.valueOf(
                        mViewTypes.get(viewTypePosition))), parent, false);
        return new DataCardAdapter.ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String type = mViewTypes.get(position);
        CardView cv = holder.getCardView();
        Data data = mData.get(mDataMap.get(type)).getData();

        switch (CardType.valueOf(type)){
            case hz:
                TextView text = cv.findViewById(R.id.text_content);
                text.setText(data.getText());
                cv.setOnClickListener(v -> mListener.onClicked(position, CardType.hz.name(), true));
                break;
            case picture:

                LinearLayout layout = cv.findViewById(R.id.picture_container);
                ImageView imageView = layout.findViewById(R.id.image);

                Picasso.get()
                        .load(data.getUrl())
                        .into(imageView);

                TextView pictureText = cv.findViewById(R.id.text_picture);
                pictureText.setText(data.getText());
                cv.setOnClickListener(v -> mListener.onClicked(position, CardType.picture.name(), true));
                break;
            case selector:
                Spinner spinner = cv.findViewById(R.id.spinner);
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<>(cv.getContext(),
                                android.R.layout.simple_spinner_item, data.getSpinnerItems());
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setSelection(data.getSelectedId());
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mListener.onClicked(position, data.getVariants().get(position).getText(), false);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;
            default:
                TextView textView = cv.findViewById(R.id.text_content);
                textView.setText(mData.get(position).getName());
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mViewTypes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(@NonNull CardView itemView) {
            super(itemView);
            cardView = itemView;
        }
        CardView getCardView(){
            return cardView;
        }
    }

    public interface CardClickListener{
        void onClicked(int position, String name, boolean isCard);
    }
}
