package test;

import java.util.LinkedHashMap;
import java.util.Map;

public class LFU implements CacheReplacementPolicy {
    private int minimum;
    private LinkedHashMap<String,Integer> cache;
    String firstword;
    public LFU() {
        this.minimum=0;
        this.cache = new LinkedHashMap<>();
    }
    @Override
    public void add(String word) {

        if(!cache.containsKey(word)) {
            if (cache.isEmpty())
                firstword = word;
            cache.put(word,1);
        }
        else
           this.cache.put(word,cache.get(word)+1);
        minimum=1;
    }

    @Override
    public String remove() {
        int minval = cache.get(firstword);
        for (Map.Entry<String,Integer> entry : cache.entrySet()) {
            if (minval > entry.getValue()) {
                minval = entry.getValue();
                firstword = entry.getKey();
            }
        }
        return firstword;
    }
}
