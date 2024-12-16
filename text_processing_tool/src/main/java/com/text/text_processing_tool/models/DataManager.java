package com.text.text_processing_tool.models;

import java.util.HashMap;
import java.util.Map;

public abstract class DataManager {
    protected Map<String, String> data = new HashMap<>();

    public  Map<String, String> getData(){
        return data;
    }

    public void setData(Map<String, String> data){
        this.data = data;
    }
    public void saveData(String key, String value) {
        if(!data.containsKey(key)){
            System.out.println("In the file");

            data.put(key, value);
        }
    }

    public abstract void updateRecord(String key, String value);
    public abstract void deleteRecord(String key);
}
