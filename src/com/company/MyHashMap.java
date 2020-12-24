package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyHashMap extends HashMap<String, List<String>> {
    @Override
    public List<String> put(String key, List<String> value) {
        String[] V = key.split(",");
        if (this.get(V[0])!= null){
            this.get(V[0]).add(V[1]);
            return this.get(V[1]);
        }
        return super.put(getKey(V[0]), getValue(V[1]));
    }

    public String getKey(String string) {
        return string.trim();
    }
    public List<String> getValue(String string){
        List<String> list = new ArrayList<>();
        list.add(string.trim());
        return list;
    }
}
