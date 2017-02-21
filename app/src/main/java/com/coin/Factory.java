package com.coin;

import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.coin.util.Assert;
import com.coin.util.CoinPrefs;
import com.coin.util.CoinServices;

/**
 * Created by user on 17-2-21.
 */

public abstract class Factory {

    // Making this volatile because on the unit tests, setInstance is called from a unit test
    // thread, and then it's read on the UI thread.
    private static volatile Factory sInatance;
    @VisibleForTesting
    protected static boolean sRegistered;
    @VisibleForTesting
    protected static boolean sInitialized;

    public static Factory get() {
        return sInatance;
    }

    public static void setInatance(Factory sInatance) {
        // Not allowed to call this after real application initialization is complete
        Assert.isTrue(sRegistered);
        Assert.isTrue(sInitialized);
        Factory.sInatance = sInatance;
    }

    public abstract void onRequiredPermissionAcquired();

    public abstract Context getApplicationContext();

    public abstract CoinServices getCoinServices();

    public abstract CoinPrefs getApplicationPrefs();

    public abstract CoinPrefs getSubscriptionPrefs(int subId);

    public abstract void OnActivityResume();



}
