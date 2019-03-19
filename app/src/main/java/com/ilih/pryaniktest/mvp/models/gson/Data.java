package com.ilih.pryaniktest.mvp.models.gson;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private String text;
    private String url;
    private int selectedId;
    private List<Variant> variants = null;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    public List<String> getSpinnerItems(){
        List<String> list = new ArrayList<>();
        for (Variant variant : variants){
            list.add(variant.getText());
        }
        return list;
    }
}
