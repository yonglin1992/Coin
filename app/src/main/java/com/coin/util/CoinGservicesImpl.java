package com.coin.util;

import android.content.Context;

/**
 * Created by user on 17-2-21.
 */

public class CoinGservicesImpl extends CoinServices {

    public CoinGservicesImpl(Context context){

    }

    @Override
    public void registerForChanges(Runnable r) {
    }

    /**
     * Asserts that the key has the expected prefix.
     */
    private void assertKeyAndWaitForGservices(final String key) {
        Assert.isTrue(key.startsWith(COIN_GSERVICES_PREFIX));
    }

    @Override
    public long getLong(String key, long defaultValue) {
        assertKeyAndWaitForGservices(key);
        return defaultValue;
    }

    @Override
    public int getInt(String key, int defaultValue) {
        assertKeyAndWaitForGservices(key);
        return defaultValue;
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        assertKeyAndWaitForGservices(key);
        return defaultValue;
    }

    @Override
    public String getString(String key, String defaultValue) {
        assertKeyAndWaitForGservices(key);
        return defaultValue;
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        assertKeyAndWaitForGservices(key);
        return defaultValue;
    }

}
