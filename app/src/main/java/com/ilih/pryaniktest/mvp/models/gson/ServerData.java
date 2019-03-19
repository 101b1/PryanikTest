package com.ilih.pryaniktest.mvp.models.gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ServerData {

    private List<Datum> data = null;
    private List<String> view = null;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public List<String> getView() {
        return view;
    }

    public void setView(List<String> view) {
        this.view = view;
    }

    public Map<String, Integer> getDataMap(){
        if(view != null){
            Map<String, Integer> map = new HashMap<>();
            int i = 0;
            for(Datum dt : data){
                map.put(dt.getName(), i);
                i++;
            }
            return map;
        }
        return null;
    }
}
