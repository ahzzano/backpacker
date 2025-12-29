package com.avery.backpacker;

public class BagManager {
    private static BagManager instance = null;
    
    private BagManager() {
        // ... constructor stuff
    }

    public static synchronized BagManager getInstance() {
        if(instance == null) {
            instance = new BagManager();
        }

        return instance;
    }

}
