/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.coin.util;


/**
 * List of gservices keys and default values which are in use.
 */
public final class CoinServicesKeys {
    private CoinServicesKeys() {}   // do not instantiate

    /**
     * Whether to enable extra debugging features on the client. Default is
     * {@value #ENABLE_DEBUGGING_FEATURES_DEFAULT}.
     */
    public static final String ENABLE_DEBUGGING_FEATURES
            = "Coin_debugging";
    public static final boolean ENABLE_DEBUGGING_FEATURES_DEFAULT
            = false;

    /**
     * Whether to enable saving extra logs. Default is {@value #ENABLE_LOG_SAVER_DEFAULT}.
     */
    public static final String ENABLE_LOG_SAVER = "coin_logsaver";
    public static final boolean ENABLE_LOG_SAVER_DEFAULT = false;

    /**
     * MMS UA profile url.
     *
     * This is used on all Android devices running Hangout, so cannot just host the profile of the
     * latest and greatest phones. However, if we're on KitKat or below we can't get the phone's
     * UA profile and thus we need to send them the default url.
     */
    public static final String MMS_UA_PROFILE_URL =
            "bugle_mms_uaprofurl";
    public static final String MMS_UA_PROFILE_URL_DEFAULT =
            "http://www.gstatic.com/android/sms/mms_ua_profile.xml";

    /**
     * Whether to use persistent, on-disk LogSaver
     */
    public static final String PERSISTENT_LOGSAVER = "coin_persistent_logsaver";
    public static final boolean PERSISTENT_LOGSAVER_DEFAULT = false;

    /**
     * Whether asserts are fatal on user/userdebug builds.
     * Default is {@value #ASSERTS_FATAL_DEFAULT}.
     */
    public static final String ASSERTS_FATAL = "bugle_asserts_fatal";
    public static final boolean ASSERTS_FATAL_DEFAULT = false;

    /**
     * For in-memory LogSaver, what's the size of memory buffer in number of records
     */
    public static final String IN_MEMORY_LOGSAVER_RECORD_COUNT =
            "coin_in_memory_logsaver_record_count";
    public static final int IN_MEMORY_LOGSAVER_RECORD_COUNT_DEFAULT = 500;

    /**
     * For on-disk LogSaver, what's the size of file rotation set
     */
    public static final String PERSISTENT_LOGSAVER_ROTATION_SET_SIZE =
            "coin_persistent_logsaver_rotation_set_size";
    public static final int PERSISTENT_LOGSAVER_ROTATION_SET_SIZE_DEFAULT = 8;

    /**
     * For on-disk LogSaver, what's the byte limit of a single log file
     */
    public static final String PERSISTENT_LOGSAVER_FILE_LIMIT_BYTES =
            "coin_persistent_logsaver_file_limit";
    public static final int PERSISTENT_LOGSAVER_FILE_LIMIT_BYTES_DEFAULT = 256 * 1024; // 256KB

    /**
     * Whether to enable transcoding GIFs. We sometimes need to compress GIFs to make them small
     * enough to send via MMS (which often limits messages to 1 MB in size).
     */
    public static final String ENABLE_GIF_TRANSCODING = "bugle_gif_transcoding";
    public static final boolean ENABLE_GIF_TRANSCODING_DEFAULT = true;

}
