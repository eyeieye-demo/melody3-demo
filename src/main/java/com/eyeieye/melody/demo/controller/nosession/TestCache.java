package com.eyeieye.melody.demo.controller.nosession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCache {
    private static Map<String,String> StringCache = new HashMap<>();
    public void put(String key, String value){
        StringCache.put(key,value);
    }
    public String get(String key){
        return StringCache.get(key);
    }
}
