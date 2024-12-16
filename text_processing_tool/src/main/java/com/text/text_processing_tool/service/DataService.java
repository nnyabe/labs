package com.text.text_processing_tool.service;

import com.text.text_processing_tool.models.DataManager;
import com.text.text_processing_tool.utils.AlertManager;

public class DataService extends DataManager {


    @Override
    public void updateRecord(String key, String value) {
        if(data.containsKey(key)){
            data.put(key, value);
        }else{
            throw new IllegalCallerException("Field not found in bookMarks");
        }
    }

    @Override
    public void deleteRecord(String key) {
        if(data.containsKey(key)){
            data.remove(key);
        }else{
            throw new IllegalCallerException("Filed not found in bookMarks");
        }
    }
}
