package com.coin;

import android.content.Context;
import android.os.Process;

import com.coin.util.Assert;
import com.coin.util.CoinApplicationPrefs;
import com.coin.util.CoinGservicesImpl;
import com.coin.util.CoinPrefs;
import com.coin.util.CoinServices;
import com.coin.util.LogUtil;
import com.coin.util.OsUtil;

/**
 * Created by user on 17-2-21.
 */

public class FactoryImpl extends Factory {

    private Context mContext;
    private CoinApplication mApplication;
    private CoinApplicationPrefs mCoinPrefs;
    private CoinServices mCoinServices;


    private FactoryImpl(){

    }

    public static Factory register(Context context,CoinApplication application){
        // This only gets called once (from BugleApplication.onCreate), but its not called in tests.
        Assert.isTrue(!sRegistered);
        Assert.isNull(Factory.get());

        final FactoryImpl factory = new FactoryImpl();
        Factory.setInatance(factory);

        factory.mApplication = application;
        factory.mContext = context;
        factory.mCoinPrefs = new CoinApplicationPrefs(context);
        factory.mCoinServices = new CoinGservicesImpl(context);
        Assert.initializeGservices(factory.mCoinServices);
        LogUtil.initializeGservices(factory.mCoinServices);

        if (OsUtil.hasRequiredPermissions()) {
            factory.onRequiredPermissionAcquired();
        }


        return factory;
    }

    @Override
    public void onRequiredPermissionAcquired() {
        if (sInitialized) {
            return;
        }
        sInitialized = true;

        mApplication.initializeSync(this);

        final Thread asyncInitialization = new Thread() {
            @Override
            public void run() {
                Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
                mApplication.initializeAsync(FactoryImpl.this);
            }
        };
        asyncInitialization.start();
    }

    @Override
    public Context getApplicationContext() {
        return mContext;
    }

    @Override
    public CoinServices getCoinServices() {
        return null;
    }

    @Override
    public CoinPrefs getApplicationPrefs() {
        return null;
    }

    @Override
    public CoinPrefs getSubscriptionPrefs(int subId) {
        return null;
    }

    @Override
    public void OnActivityResume() {

    }

}
