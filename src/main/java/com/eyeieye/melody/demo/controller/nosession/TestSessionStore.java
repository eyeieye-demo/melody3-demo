package com.eyeieye.melody.demo.controller.nosession;

import com.eyeieye.melos.web.nosession.SessionStore;

import java.util.HashSet;
import java.util.Set;

public class TestSessionStore implements SessionStore {

    Set<String> attributeNames = new HashSet<>();

    public Set<String> getAttributeNames() {
        if(attributeNames.size() == 0){
            attributeNames.add("_test");
        }
        return attributeNames;
    }

    public void setAttributeNames(Set<String> attributeNames) {
        this.attributeNames = attributeNames;
    }

    TestSessionStore TestSessionStore(){
        return new TestSessionStore();
    }


    TestCache testCache = new TestCache();

    @Override
    public void put(String sessionId, String key, Object value) {
        testCache.put(sessionId+key,(String)value);
    }

    @Override
    public Object get(String sessionId, String key) {
        return testCache.get(sessionId+key);
    }

    @Override
    public Set<String> keys() {
        return attributeNames;
    }

    @Override
    public void invalidate(String sessionId) {

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
