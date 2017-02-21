package com.coin.util;

import android.content.Context;

/**
 * Created by user on 17-2-21.
 */

public class CoinApplicationPrefs extends CoinPrefsImpl {


    public CoinApplicationPrefs(Context context) {
        super(context);
    }

    @Override
    public String getSharedPreferencesName() {
        return SHARED_PREFERENCES_NAME;
    }

    @Override
    protected void validateKey(String key) {
        super.validateKey(key);
        // Callers shouldn't try to access per-subscription preferences from this class
        Assert.isTrue(key.startsWith(SHARED_PREFERENCES_PER_SUBSCRIPTION_PREFIX));
    }

    @Override
    public void onUpgrade(int oldVersion, int newVersion) {

    }
}
