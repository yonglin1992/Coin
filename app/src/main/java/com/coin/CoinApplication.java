package com.coin;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.VisibleForTesting;

import com.coin.util.CoinPrefs;
import com.coin.util.CoinPrefsKeys;
import com.coin.util.CoinServices;
import com.coin.util.LogUtil;
import com.coin.util.Trace;

/**
 * Created by user on 17-2-21.
 */

public class CoinApplication extends Application implements Thread.UncaughtExceptionHandler{

    private static final String TAG = LogUtil.COIN_TAG;

    private Thread.UncaughtExceptionHandler sSystemUncaughtExceptionHandler;
    private static boolean sRunningTests = false;

    @VisibleForTesting
    protected static void setTestsRunning() {
        sRunningTests = true;
    }

    /**
     * @return true if we're running unit tests.
     */
    public static boolean isRunningTests() {
        return sRunningTests;
    }


    @Override
    public void onCreate() {
        Trace.beginSection("app.onCreate");
        super.onCreate();
        if (!sRunningTests) {
            // Only create the factory if not running tests
            FactoryImpl.register(getApplicationContext(), this);
        } else {
            LogUtil.e(TAG, "BugleApplication.onCreate: FactoryImpl.register skipped for test run");
        }

        sSystemUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        Trace.endSection();

    }

    // Called from thread started in FactoryImpl.register() (i.e. not run in tests)
    public void initializeAsync(final Factory factory) {
        // Handle shared prefs upgrade & Load MMS Configuration
        Trace.beginSection("app.initializeAsync");
//        maybeHandleSharedPrefsUpgrade(factory);
//        MmsConfig.load();
        Trace.endSection();
    }

    // Called by the "real" factory from FactoryImpl.register() (i.e. not run in tests)
    public void initializeSync(final Factory factory) {
        Trace.beginSection("app.initializeSync");
        final Context context = factory.getApplicationContext();
        final CoinServices bugleGservices = factory.getCoinServices();
        final CoinPrefs buglePrefs = factory.getApplicationPrefs();
//        final DataModel dataModel = factory.getDataModel();
//        final CarrierConfigValuesLoader carrierConfigValuesLoader =
//                factory.getCarrierConfigValuesLoader();
//
//        maybeStartProfiling();
//
//        BugleApplication.updateAppConfig(context);
//
//        // Initialize MMS lib
//        initMmsLib(context, bugleGservices, carrierConfigValuesLoader);
//        // Initialize APN database
//        ApnDatabase.initializeAppContext(context);
//        // Fixup messages in flight if we crashed and send any pending
//        dataModel.onApplicationCreated();
//        // Register carrier config change receiver
//        if (OsUtil.isAtLeastM()) {
//            registerCarrierConfigChangeReceiver(context);
//        }

        Trace.endSection();
    }

    @Override
    public void uncaughtException(final Thread thread, final Throwable ex) {
        final boolean background = getMainLooper().getThread() != thread;
        if (background) {
            LogUtil.e(TAG, "Uncaught exception in background thread " + thread, ex);

            final Handler handler = new Handler(getMainLooper());
            handler.post(new Runnable() {

                @Override
                public void run() {
                    sSystemUncaughtExceptionHandler.uncaughtException(thread, ex);
                }
            });
        } else {
            sSystemUncaughtExceptionHandler.uncaughtException(thread, ex);
        }
    }

    private void maybeHandleSharedPrefsUpgrade(final Factory factory) {
        final int existingVersion = factory.getApplicationPrefs().getInt(
                CoinPrefsKeys.SHARED_PREFERENCES_VERSION,
                CoinPrefsKeys.SHARED_PREFERENCES_VERSION_DEFAULT);
        final int targetVersion = Integer.parseInt(getString(R.string.pref_version));
        if (targetVersion > existingVersion) {
            LogUtil.i(LogUtil.COIN_TAG, "Upgrading shared prefs from " + existingVersion +
                    " to " + targetVersion);
            try {
                // Perform upgrade on application-wide prefs.
                factory.getApplicationPrefs().onUpgrade(existingVersion, targetVersion);
                // Perform upgrade on each subscription's prefs.
//                PhoneUtils.forEachActiveSubscription(new PhoneUtils.SubscriptionRunnable() {
//                    @Override
//                    public void runForSubscription(final int subId) {
//                        factory.getSubscriptionPrefs(subId)
//                                .onUpgrade(existingVersion, targetVersion);
//                    }
//                });
                factory.getApplicationPrefs().putInt(CoinPrefsKeys.SHARED_PREFERENCES_VERSION,
                        targetVersion);
            } catch (final Exception ex) {
                // Upgrade failed. Don't crash the app because we can always fall back to the
                // default settings.
                LogUtil.e(LogUtil.COIN_TAG, "Failed to upgrade shared prefs", ex);
            }
        } else if (targetVersion < existingVersion) {
            // We don't care about downgrade since real user shouldn't encounter this, so log it
            // and ignore any prefs migration.
            LogUtil.e(LogUtil.COIN_TAG, "Shared prefs downgrade requested and ignored. " +
                    "oldVersion = " + existingVersion + ", newVersion = " + targetVersion);
        }
    }


}
