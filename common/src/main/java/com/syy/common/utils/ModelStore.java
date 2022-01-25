package com.syy.common.utils;

import android.util.SparseArray;

public class ModelStore {
    private static ModelStore instance;
    private SparseArray<Object> models = new SparseArray<>();

    public synchronized static ModelStore getInstance() {
        if (instance == null) {
            instance = new ModelStore();
        }
        return instance;
    }

    public synchronized int put(Object obj) {
        if (obj == null) {
            return 0;
        }
        int key = obj.hashCode();
        models.put(key, obj);
        return key;
    }

    public synchronized Object pop(int key) {
        Object obj = models.get(key);
        models.remove(key);
        return obj;
    }

    public synchronized boolean fetch(int key) {
        return models.get(key) != null;
    }
}
