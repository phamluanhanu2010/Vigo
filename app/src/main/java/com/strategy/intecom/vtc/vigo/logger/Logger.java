/*
 * Created by Hadvlop@gmail.com on 4/12/17 11:35 AM
 * Copyright © 2017, All Rights Reserved.
 *
 * Last modified 3/22/17 6:00 PM
 */

package com.strategy.intecom.vtc.vigo.logger;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.strategy.intecom.vtc.vigo.BuildConfig;

/**
 * Created by macbookpro on 3/7/17.
 *
 * @author Mr. Ha
 */

public class Logger {


    /**
     * Show Log toast
     * Enable when DEBUG is true
     *
     * @param context
     * @param msg  content message
     */
    public static void showLogToast(Context context, String msg) {
        if (BuildConfig.DEBUG) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
//            Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    /**
     * Show toast Long for message
     *
     * @param context
     * @param msg  content message
     */
    public static void showToastLong(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
//        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    /**
     * Show toast Short for message
     *
     * @param context
     * @param msg  content message
     */
    public static void showToastShort(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
//        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    /**
     * Show Log Bug
     * Enable when DEBUG is true
     *
     * @param msg content log
     */
    public static void showLog(String msg) {
        if (BuildConfig.DEBUG) {
            Log.i("Ha : ", "----------------- : " + msg);
        }
    }

    /**
     * Show Log Bug for Tag
     * Enable when DEBUG is true
     *
     * @param tag Tag Name
     * @param msg content log
     */
    public static void showLog(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, "----------------- : " + msg);
        }
    }
}
