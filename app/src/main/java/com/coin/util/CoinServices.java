package com.coin.util;

import com.coin.Factory;

/**
 * Created by user on 17-2-21.
 */

public abstract class CoinServices {

    static final String COIN_GSERVICES_PREFIX = "coin_";

    public static CoinServices get() {
        return Factory.get().getCoinServices();
    }

    public abstract void registerForChanges(final Runnable r);

    /**
     * @param key The key to look up in GServices
     * @param defaultValue The default value if value in GServices is null or if
     * NumberFormatException is caught.
     * @return The corresponding value, or the default value.
     */
    public abstract long getLong(final String key, final long defaultValue);

    /**
     * @param key The key to look up in GServices
     * @param defaultValue The default value if value in GServices is null or if
     * NumberFormatException is caught.
     * @return The corresponding value, or the default value.
     */
    public abstract int getInt(final String key, final int defaultValue);

    /**
     * @param key The key to look up in GServices
     * @param defaultValue The default value if value in GServices is null.
     * @return The corresponding value, or the default value.
     */
    public abstract boolean getBoolean(final String key, final boolean defaultValue);

    /**
     * @param key The key to look up in GServices
     * @param defaultValue The default value if value in GServices is null.
     * @return The corresponding value, or the default value.
     */
    public abstract String getString(final String key, final String defaultValue);

    /**
     * @param key The key to look up in GServices
     * @param defaultValue The default value if value in GServices is null.
     * @return The corresponding value, or the default value.
     */
    public abstract float getFloat(final String key, final float defaultValue);

}
