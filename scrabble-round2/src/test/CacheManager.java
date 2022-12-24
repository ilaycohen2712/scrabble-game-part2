package test;


import java.util.LinkedHashSet;

public class CacheManager {
    int cacheSize;
    private LinkedHashSet<String> cacheWords;
    CacheReplacementPolicy crp;
    public CacheManager(int cacheSize, CacheReplacementPolicy crp) {
        this.cacheSize =cacheSize;
        this.cacheWords = new LinkedHashSet<>();
        this.crp = crp;
    }

    public boolean query(String a) {
        if(cacheWords.contains(a))
            return true;
        else return false;
    }

    public void add(String c) {
        crp.add(c);
        cacheWords.add(c);
        if(cacheWords.size()>cacheSize)
        {
            cacheWords.remove(crp.remove());
        }
    }
}
